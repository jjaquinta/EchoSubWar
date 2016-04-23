package com.tsatsatzu.subwar.test.game;

import static org.junit.Assert.*;

import org.junit.Test;

import com.tsatsatzu.subwar.game.logic.CredentialsLogic;

public class CredentialTest
{
    @Test
    public void test()
    {
        assertNotNull("secretKey not set in system secrets", CredentialsLogic.getProperty("secretKey"));
        assertNotNull("accessKey not set in system secrets", CredentialsLogic.getProperty("accessKey"));
        assertNotNull("apiKeys not set in system secrets", CredentialsLogic.getProperty("apiKeys"));
    }
}
