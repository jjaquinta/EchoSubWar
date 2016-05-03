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

public class SubWarServlet extends SpeechletServlet
{
    /**
     * 
     */
    private static final long serialVersionUID = 6998188851979224629L;

    static
    {
        System.setProperty(Sdk.DISABLE_REQUEST_SIGNATURE_CHECK_SYSTEM_PROPERTY, "true");
        System.setProperty(Sdk.SUPPORTED_APPLICATION_IDS_SYSTEM_PROPERTY, "");
        System.setProperty(Sdk.TIMESTAMP_TOLERANCE_SYSTEM_PROPERTY, "");
    }
    
    private Throwable mLastException = null;
    private static StringBuffer mLogMessages = new StringBuffer();
    private static Map<Thread, HttpServletRequest> mRequests = new HashMap<Thread, HttpServletRequest>();

    private static SubWarServlet mInstance = null;
    
    public SubWarServlet()
    {
        mInstance = this;
    }
    
    @Override
    public void init(ServletConfig config) throws ServletException
    {
        super.init(config);
        this.setSpeechlet(new SubWarSpeechlet());
        System.setProperty("aws.accessKeyId",  "AKIAJBFQIVVXXOXKPQLA");
        System.setProperty("aws.secretKey",  "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");
    }
    
    public static void debug(Throwable t)
    {
        for (Throwable e = t; e != null; e = e.getCause())
        {
            debug(e.toString());
            for (StackTraceElement ele : e.getStackTrace())
                debug("  "+ele.toString());
        }
    }
    
    public static void debug(String msg)
    {
        mLogMessages.append(msg);
        mLogMessages.append("\r\n");
        System.out.println(msg);
    }

    @Override
    public void setSpeechlet(Speechlet speechlet)
    {
        super.setSpeechlet(new SpeechletWrapper(getClass(), speechlet));
    }

    @Override
    public Speechlet getSpeechlet()
    {
        Speechlet base = super.getSpeechlet();
        if (base instanceof SpeechletWrapper)
            base = ((SpeechletWrapper)base).getBase();
        return base;
    }
    
    @Override
    protected void doPost(HttpServletRequest arg0, HttpServletResponse arg1)
            throws IOException
    {
        try
        {
            mRequests.put(Thread.currentThread(), arg0);
            super.doPost(arg0, arg1);
            mRequests.remove(Thread.currentThread());
        }
        catch (Exception e1)
        {
            log("Post error.");
            debug(e1);
            throw e1;
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        String fetch = req.getParameter("fetch");
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
    
    class SpeechletWrapper implements Speechlet {
        private Class<?>  mServlet;
        private Speechlet mBase;
        
        public SpeechletWrapper(Class<?> servlet, Speechlet base)
        {
            mServlet = servlet;
            mBase = base;
        }
        
        @Override
        public void onSessionStarted(final SessionStartedRequest request, final Session session)
                throws SpeechletException {
            //log(mServlet, "onSessionStarted requestId="+request.getRequestId()+", sessionId="+session.getSessionId());
            mBase.onSessionStarted(request, session);
        }

        @Override
        public SpeechletResponse onLaunch(final LaunchRequest request, final Session session)
                throws SpeechletException {
            //log(mServlet, "onLaunch requestId="+request.getRequestId()+", sessionId="+session.getSessionId());
            SpeechletResponse response = mBase.onLaunch(request, session);
            logResponse(response);
            return response;
        }

        @Override
        public SpeechletResponse onIntent(final IntentRequest request, final Session session)
                throws SpeechletException {
            try
            {
                //log(mServlet, "onIntent requestId="+request.getRequestId()+", sessionId="+session.getSessionId());
                if (request.getIntent() != null)
                {
                    StringBuffer sb = new StringBuffer();
                    sb.append(request.getIntent().getName()+":");
                    for (Slot s : request.getIntent().getSlots().values())
                        sb.append(" "+s.getName()+"="+s.getValue());
                    SubWarServlet.debug(sb.toString());
                }
                SpeechletResponse response = mBase.onIntent(request, session);
                //log(mServlet, "onIntent returning");
                logResponse(response);
                return response;
            }
            catch (Throwable t)
            {
                SubWarServlet.debug(t);
                throw t;
            }
        }

        @Override
        public void onSessionEnded(final SessionEndedRequest request, final Session session)
                throws SpeechletException {
            SubWarServlet.debug("onSessionEnded requestId="+request.getRequestId()+", sessionId="+session.getSessionId());
            mBase.onSessionEnded(request, session);
        }

        private void logResponse(SpeechletResponse response)
        {
            if (response.getOutputSpeech() instanceof PlainTextOutputSpeech)
                SubWarServlet.debug(((PlainTextOutputSpeech)response.getOutputSpeech()).getText());
        }
        
        public Speechlet getBase()
        {
            return mBase;
        }

        public void setBase(Speechlet base)
        {
            mBase = base;
        }
    }

}
