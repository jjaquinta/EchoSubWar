package com.tsatsatzu.subwar.test.game;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AITest.class, AngleTest.class, AttackTest.class,
        CredentialTest.class, MoveTest.class, ObserveTest.class,
        UserTest.class })
public class AllGameTests
{

}
