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
package com.tsatsatzu.subwar.game.logic.dynamo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.amazonaws.auth.SystemPropertiesCredentialsProvider;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.DeleteItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemRequest;
import com.amazonaws.services.dynamodbv2.model.GetItemResult;
import com.amazonaws.services.dynamodbv2.model.PutItemRequest;
import com.amazonaws.services.dynamodbv2.model.QueryRequest;
import com.amazonaws.services.dynamodbv2.model.QueryResult;
import com.tsatsatzu.subwar.game.api.SubWarGameAPI;
import com.tsatsatzu.subwar.game.data.SWUserBean;
import com.tsatsatzu.subwar.game.logic.CredentialsLogic;
import com.tsatsatzu.subwar.game.logic.IIODriver;

// TODO: Auto-generated Javadoc
/*
 * This I/O driver is used for persisting values in a set of DynamoDB databases.
 */

/**
 * The Class DynamoIODriver.
 */
public class DynamoIODriver implements IIODriver
{
    
    /** The Client. */
    private AmazonDynamoDBClient mClient;
    
    /** The Constant USER_TABLE_NAME. */
    public static final String USER_TABLE_NAME = "SubWarUsers";
    
    /** The Constant USER_KILLS_INDEX. */
    public static final String USER_KILLS_INDEX = "numerofkills-index";
    
    /** The Constant USER_PRIMARY_KEY. */
    private static final String USER_PRIMARY_KEY = "UserID";

    /** The User cache. */
    private Map<String, SWUserBean> mUserCache = new HashMap<>();
    
    /** The User cache fetch. */
    private Map<String, Long> mUserCacheFetch = new HashMap<>();

    /** The fetch timeout. */
    private int FETCH_TIMEOUT = 1000; // latency for almost-continuous
    
    /** The Output queue index. */
    private Set<String> mOutputQueueIndex = new HashSet<String>();
    
    /** The Output queue. */
    private List<Object> mOutputQueue = new LinkedList<Object>();
    
    /** The Output queue thread. */
    private Thread mOutputQueueThread = null;
    
    /** The Single threaded. */
    private boolean mSingleThreaded;
    
    /**
     * Instantiates a new dynamo io driver.
     */
    public DynamoIODriver()
    {
        String accessKey = CredentialsLogic.getProperty("accessKey");
        String secretKey = CredentialsLogic.getProperty("secretKey");
        System.setProperty("aws.accessKeyId",  accessKey);
        System.setProperty("aws.secretKey",  secretKey);
        mClient = new AmazonDynamoDBClient(new SystemPropertiesCredentialsProvider());
        mSingleThreaded = true;
    }

    /**
     * Instantiates a new dynamo io driver.
     *
     * @param singleThreaded the single threaded
     */
    public DynamoIODriver(boolean singleThreaded)
    {
        this();
        mSingleThreaded = singleThreaded;
    }

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.IIODriver#clearCaches()
     */
    @Override
    public void clearCaches()
    {
        mUserCache.clear();
        mUserCacheFetch.clear();
    }

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.IIODriver#getUser(java.lang.String)
     */
    @Override
    public SWUserBean getUser(String id)
    {
        SWUserBean user = mUserCache.get(id);
        if (user != null)
        {
            Long lastFetch = mUserCacheFetch.get(id);
            if (lastFetch != null)
            {
                long elapsed = System.currentTimeMillis() - lastFetch;
                if (elapsed < FETCH_TIMEOUT)
                    return user;
            }
        }
        synchronized (mOutputQueueIndex)
        {
            if (mOutputQueueIndex.contains(id))
                for (Object o : mOutputQueue)
                    if ((o instanceof SWUserBean) && id.equals(((SWUserBean)o).getUserID()))
                        return (SWUserBean)o;
        }
        Map<String, AttributeValue> key = new HashMap<String, AttributeValue>();
        key.put(USER_PRIMARY_KEY, new AttributeValue().withS(id));
        GetItemRequest getItemRequest = new GetItemRequest().withTableName(USER_TABLE_NAME).withKey(key);
        GetItemResult result = mClient.getItem(getItemRequest);
        if (result.getItem() == null)
            return null;
        if (user == null)
        {
            user = new SWUserBean();
            mUserCache.put(id, user);
        }
        user.fromMap(result.getItem());
        mUserCacheFetch.put(user.getUserID(), System.currentTimeMillis());
        return user;
    }

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.IIODriver#saveUser(com.tsatsatzu.subwar.game.data.SWUserBean)
     */
    @Override
    public void saveUser(SWUserBean user)
    {
        mUserCache.put(user.getUserID(), user);
        mUserCacheFetch.put(user.getUserID(), System.currentTimeMillis());
        if (mSingleThreaded)
            doSaveUser(user);
        else
            addToOutputQueue(user.getUserID(), user);
    }
    
    /**
     * Do save user.
     *
     * @param user the user
     */
    private void doSaveUser(SWUserBean user)
    {
        user.setNumberOfInteractions(user.getNumberOfInteractions() + 1);
        user.setLastInteraction(System.currentTimeMillis());
        Map<String, AttributeValue> item = user.toMap();
        PutItemRequest itemRequest = new PutItemRequest().withTableName(USER_TABLE_NAME).withItem(item);
        mClient.putItem(itemRequest);
    }

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.IIODriver#deleteUser(java.lang.String)
     */
    @Override
    public void deleteUser(String id)
    {
        Map<String, AttributeValue> key = new HashMap<>();
        key.put(USER_PRIMARY_KEY, new AttributeValue().withS(id));
        DeleteItemRequest request = new DeleteItemRequest(USER_TABLE_NAME, key);
        mClient.deleteItem(request);
    }

    /* (non-Javadoc)
     * @see com.tsatsatzu.subwar.game.logic.IIODriver#getTopUsers(int)
     */
    @Override
    public List<SWUserBean> getTopUsers(int total)
    {
        List<SWUserBean> top = new ArrayList<>();
        QueryRequest queryRequest = new QueryRequest()
                .withTableName(USER_TABLE_NAME)
                .withLimit(total)
                .withScanIndexForward(false);
        QueryResult result = mClient.query(queryRequest);
        for (Map<String,AttributeValue> item : result.getItems())
        {
            SWUserBean user = new SWUserBean();
            user.fromMap(item);
            if (mUserCache.containsKey(user.getUserID()))
            {
                user = mUserCache.get(user.getUserID());
                user.fromMap(item);
            }
            else
                mUserCache.put(user.getUserID(), user);
            mUserCacheFetch.put(user.getUserID(), System.currentTimeMillis());
            top.add(user);
        }
        return top;
    }
    
    /**
     * Adds the to output queue.
     *
     * @param idx the idx
     * @param obj the obj
     */
    private void addToOutputQueue(String idx, Object obj)
    {
        synchronized (mOutputQueueIndex)
        {
            if (mOutputQueueIndex.contains(idx))
                return; // assume objects the same
            mOutputQueueIndex.add(idx);
            mOutputQueue.add(obj);
            if ((mOutputQueueThread == null) || !mOutputQueueThread.isAlive())
            {
                mOutputQueueThread = new Thread("OutputWriter") { public void run() { runOutput(); } };
                mOutputQueueThread.start();
            }
        }
    }

    /**
     * Gets the from output queue.
     *
     * @return the from output queue
     */
    private Object getFromOutputQueue()
    {
        if (mOutputQueue.size() == 0)
            return null;
        Object obj = mOutputQueue.get(0);
        mOutputQueue.remove(0);
        if (obj instanceof SWUserBean)
            mOutputQueueIndex.remove(((SWUserBean)obj).getUserID());
        else
            SubWarGameAPI.debug("Cannot write a "+obj.getClass().getName());
        return obj;
    }
    
    /**
     * Run output.
     */
    private void runOutput()
    {
        for (;;)
        {
            Object next = null;
            synchronized (mOutputQueueIndex)
            {
                next = getFromOutputQueue();
                if (next == null)
                {
                    mOutputQueueThread = null;
                    return;
                }
            }
            if (next instanceof SWUserBean)
                doSaveUser((SWUserBean)next);
            else
                SubWarGameAPI.debug("Cannot write a "+next.getClass().getName());
        }
    }

}
