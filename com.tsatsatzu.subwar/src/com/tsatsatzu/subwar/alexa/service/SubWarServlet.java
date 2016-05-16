/*
 * Copyright 2016 Jo Jaquinta, TsaTsaTzu
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.tsatsatzu.subwar.alexa.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.amazon.speech.Sdk;
import com.amazon.speech.slu.Slot;
import com.amazon.speech.speechlet.IntentRequest;
import com.amazon.speech.speechlet.LaunchRequest;
import com.amazon.speech.speechlet.Session;
import com.amazon.speech.speechlet.SessionEndedRequest;
import com.amazon.speech.speechlet.SessionStartedRequest;
import com.amazon.speech.speechlet.Speechlet;
import com.amazon.speech.speechlet.SpeechletException;
import com.amazon.speech.speechlet.SpeechletResponse;
import com.amazon.speech.speechlet.servlet.SpeechletServlet;
import com.amazon.speech.ui.PlainTextOutputSpeech;
import com.tsatsatzu.subwar.audio.api.ISubWarAudioLogger;
import com.tsatsatzu.subwar.audio.api.SubWarAudioAPI;

/**
 * The Class SubWarServlet.
 * This is the servlet entry point. It adds a few features to the basic one supplied by the Alexa Skills Kit.
 */
public class SubWarServlet extends SpeechletServlet
{
    
    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 6998188851979224629L;

    /** The Constant ALEXA_CREDENTIALS. */
    protected static final String ALEXA_CREDENTIALS = "nWZMvNicrfOU2fGpPGs1rrrdYEKMRa58xL7GZSuK9/hgd3kCQQDy5s714jiXa1EH";
    
    /** Turn off a bunch of extra checks. We're not paranoid, and they interfere with automated testing. */
    static
    {
        System.setProperty(Sdk.DISABLE_REQUEST_SIGNATURE_CHECK_SYSTEM_PROPERTY, "true");
        System.setProperty(Sdk.SUPPORTED_APPLICATION_IDS_SYSTEM_PROPERTY, "");
        System.setProperty(Sdk.TIMESTAMP_TOLERANCE_SYSTEM_PROPERTY, "");
    }
    
    /** The Last exception. */
    private Throwable mLastException = null;
    
    /** The Log messages. */
    private static StringBuffer mLogMessages = new StringBuffer();
    
    /** The Requests. */
    private static Map<Thread, HttpServletRequest> mRequests = new HashMap<Thread, HttpServletRequest>();

    /**
     * Instantiates a new sub war servlet.
     * Initiates the data store, and sets up the debug channels.
     */
    public SubWarServlet()
    {
        // Set I/O driver to the memory model.
        // Testing is going to screw with data.
        // We don't want to save that to Dynamo.
        System.setProperty("ioDriver", "com.tsatsatzu.subwar.game.logic.mem.MemIODriver");
        // Since everything is running in the same memory instance, we
        // can set our own key here.
        System.setProperty("audio.api.key", ALEXA_CREDENTIALS);
        System.setProperty("apiKeys", ALEXA_CREDENTIALS);
        SubWarAudioAPI.setLogger(new ISubWarAudioLogger() {            
            @Override
            public void debug(Throwable t)
            {
                SubWarServlet.debug(t);
            }
            @Override
            public void debug(String msg)
            {
                SubWarServlet.debug(msg);
            }
        });
    }
    
    /* (non-Javadoc)
     * @see javax.servlet.GenericServlet#init(javax.servlet.ServletConfig)
     */
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        this.setSpeechlet(new SubWarSpeechlet());
    }
    
    /**
     * Debug.
     * Report a throwable.
     *
     * @param t the exception
     */
    public static void debug(Throwable t)
    {
        for (Throwable e = t; e != null; e = e.getCause())
        {
            debug(e.toString());
            for (StackTraceElement ele : e.getStackTrace())
                debug("  "+ele.toString());
        }
    }
    
    /**
     * Debug.
     * Report a message.
     *
     * @param msg the message
     */
    public static void debug(String msg)
    {
        mLogMessages.append(msg);
        mLogMessages.append("\r\n");
        System.out.println(msg);
    }

    /* (non-Javadoc)
     * @see com.amazon.speech.speechlet.servlet.SpeechletServlet#setSpeechlet(com.amazon.speech.speechlet.Speechlet)
     */
    @Override
    public void setSpeechlet(Speechlet speechlet)
    {
        super.setSpeechlet(new SpeechletWrapper(speechlet));
    }

    /* (non-Javadoc)
     * @see com.amazon.speech.speechlet.servlet.SpeechletServlet#getSpeechlet()
     */
    @Override
    public Speechlet getSpeechlet()
    {
        Speechlet base = super.getSpeechlet();
        if (base instanceof SpeechletWrapper)
            base = ((SpeechletWrapper)base).getBase();
        return base;
    }
    
    /* (non-Javadoc)
     * @see com.amazon.speech.speechlet.servlet.SpeechletServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException
    {
        try
        {
            mRequests.put(Thread.currentThread(), req);
            super.doPost(req, resp);
            mRequests.remove(Thread.currentThread());
        }
        catch (Exception e1)
        {
            log("Post error.");
            debug(e1);
            throw e1;
        }
    }
    
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        if ((mLastException != null) || (mLogMessages.length() > 0))
        {
            resp.setContentType("text/html");
            resp.getWriter().append("<html>\r\n");
            resp.getWriter().append("<body>\r\n");
            if (mLogMessages.length() > 0)
            {
                String lastUser = null;
                for (StringTokenizer st = new StringTokenizer(mLogMessages.toString(), "\r\n"); st.hasMoreTokens(); )
                {
                    String line = st.nextToken();
                    int o = line.indexOf(":");
                    if (o >= 0)
                    {
                        String label = line.substring(0, o).trim();
                        String msg = line.substring(o + 1).trim();
                        if ("user".equalsIgnoreCase(label))
                        {
                            if (!msg.equals(lastUser))
                            {
                                resp.getWriter().write("<hr/>\r\n");
                                line = "<h3>"+insertEntities(msg)+"</h3>";
                                lastUser = msg;
                            }
                        }
                        else
                            line = "<b>"+insertEntities(label)+":</b> "+insertEntities(msg);
                    }
                    else
                        line = insertEntities(line);
                    resp.getWriter().write(line+"<br/>\r\n");
                }
                mLogMessages.setLength(0);
            }
            Throwable t = mLastException;
            while (t != null)
            {
                resp.getWriter().append("<pre>\r\n");
                resp.getWriter().append(t.getLocalizedMessage()+":\r\n");
                for (StackTraceElement ste : t.getStackTrace())
                    resp.getWriter().append("  "+ste.toString()+":\r\n");
                if (t instanceof Exception)
                    t = ((Exception)t).getCause();
                else
                    t = null;
                resp.getWriter().append("</pre>\r\n");
            }
            resp.getWriter().append("</body>\r\n");
            resp.getWriter().append("</html>\r\n");
        }        
        else
        {
            resp.setContentType("text/plain");
            resp.getWriter().append("Hello from the "+getClass().getSimpleName()+" app.");
        }
    }

    /**
     * Insert entities.
     * Parses a string to be HTML friendly
     *
     * @param txt the txt
     * @return the sanitized string
     */
    public static String insertEntities(String txt)
    {
        if (txt == null)
            return null;
        StringBuffer ret = new StringBuffer();
        char[] c = txt.toCharArray();
        for (int i = 0; i < c.length; i++)
            if (c[i] == '&')
                ret.append("&amp;");
            else if (c[i] == '<')
                ret.append("&lt;");
            else if (c[i] == '>')
                ret.append("&gt;");
            else if (c[i] == '\"')
                ret.append("&quot;");
            else if ((c[i] >= 0x20) && (c[i] < 0x80))
                ret.append(c[i]);
            else if (Character.isWhitespace(c[i]))
                ret.append(c[i]);
            else
            {
                ret.append("&#x");
                ret.append(Integer.toHexString(c[i]));
                ret.append(";");
            }
        return ret.toString();
    }
    
    /**
     * The Class SpeechletWrapper.
     * This wraps any Alexa Speechlet object and adds some extra logging and exception handling to it.
     */
    class SpeechletWrapper implements Speechlet {
        
        /** The Base. */
        private Speechlet mBase;
        
        /**
         * Instantiates a new speechlet wrapper.
         *
         * @param base the base speechlet
         */
        public SpeechletWrapper(Speechlet base)
        {
            mBase = base;
        }
        
        /* (non-Javadoc)
         * @see com.amazon.speech.speechlet.Speechlet#onSessionStarted(com.amazon.speech.speechlet.SessionStartedRequest, com.amazon.speech.speechlet.Session)
         */
        @Override
        public void onSessionStarted(final SessionStartedRequest request, final Session session)
                throws SpeechletException {
            mBase.onSessionStarted(request, session);
        }

        /* (non-Javadoc)
         * @see com.amazon.speech.speechlet.Speechlet#onLaunch(com.amazon.speech.speechlet.LaunchRequest, com.amazon.speech.speechlet.Session)
         */
        @Override
        public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
                throws SpeechletException {
            SpeechletResponse response = mBase.onLaunch(request, session);
            logResponse(response);
            return response;
        }

        /* (non-Javadoc)
         * @see com.amazon.speech.speechlet.Speechlet#onIntent(com.amazon.speech.speechlet.IntentRequest, com.amazon.speech.speechlet.Session)
         */
        @Override
        public SpeechletResponse onIntent(final IntentRequest request, final Session session)
                throws SpeechletException {
            try
            {
                if (request.getIntent() != null)
                {
                    StringBuffer sb = new StringBuffer();
                    sb.append(request.getIntent().getName()+":");
                    for (Slot s : request.getIntent().getSlots().values())
                        sb.append(" "+s.getName()+"="+s.getValue());
                    SubWarServlet.debug(sb.toString());
                }
                SpeechletResponse response = mBase.onIntent(request, session);
                logResponse(response);
                return response;
            }
            catch (Throwable t)
            {
                SubWarServlet.debug(t);
                throw t;
            }
        }

        /* (non-Javadoc)
         * @see com.amazon.speech.speechlet.Speechlet#onSessionEnded(com.amazon.speech.speechlet.SessionEndedRequest, com.amazon.speech.speechlet.Session)
         */
        @Override
        public void onSessionEnded(final SessionEndedRequest request, final Session session)
                throws SpeechletException {
            mBase.onSessionEnded(request, session);
            SubWarServlet.debug("onSessionEnded done");
        }

        /**
         * Log response.
         * Direct logging to our handlers.
         *
         * @param response the response
         */
        private void logResponse(SpeechletResponse response)
        {
            if (response.getOutputSpeech() instanceof PlainTextOutputSpeech)
                SubWarServlet.debug(((PlainTextOutputSpeech)response.getOutputSpeech()).getText());
        }
        
        /**
         * Gets the base.
         *
         * @return the base
         */
        public Speechlet getBase()
        {
            return mBase;
        }

        /**
         * Sets the base.
         *
         * @param base the new base
         */
        public void setBase(Speechlet base)
        {
            mBase = base;
        }
    }

}
