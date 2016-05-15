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
package com.tsatsatzu.subwar.test.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tsatsatzu.subwar.game.logic.CredentialsLogic;

// TODO: Auto-generated Javadoc
/**
 * The Class CredentialTest.
 */
public class CredentialTest
{
    
    /**
     * Test.
     */
    @Test
    public void test()
    {
        assertNotNull("secretKey not set in system secrets", CredentialsLogic.getProperty("secretKey"));
        assertNotNull("accessKey not set in system secrets", CredentialsLogic.getProperty("accessKey"));
        assertNotNull("apiKeys not set in system secrets", CredentialsLogic.getProperty("apiKeys"));
    }
}
