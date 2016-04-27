# Test Suite

This project contains the JUnit tests that verify the functionality of the different layers of SubWar.
Although not explicitly using Test Driven Development, the aim here is to validate functionality as it is added.
Collectively the tests provide a regression suite to ensure that subsequent features do not break any existing functionality.

When a test is launched, it institutes the in-memory storage model, and adds an override to specify a unique API key.
This means that the Dynamo storage is not changed by testing, and that any amount of constructive or destructive tesing can be done.
It also means that the credentials file does not need to be changed. I.e. a special test key and secret do not have
to be maintained to validate the test routines with.