# EchoSubWar

This is [TsaTsaTzu's](https://www.tsatsatzu.com) entry for 
the [Hackster.io Alexa skills contest](https://www.hackster.io/challenges/amazon-alexa-skill-contest-one).
Since all entrants are required to be published, we will not be pursuing this commercially after the contest is over,
and will be donating it to the community.
We would like to see more high quality skills available that make the most of the Alexa platform.
It is hoped that by providing this, users will be able to take the techniques shown here and use them to better
their own skills. Or that people will contribute to make this skill even better than it is.

## Gameplay

You are the captain of a Colella Class Hunter/Killer submarine.
You patrol the Acton straits, seeking to torpedo any other submarine you come across.
You can issue commands to your 2nd in command, Lieuenant Alexa, to move, listen, do a sonar ping
or fire one of your torpedoes.

Other players may be playing the game at the same time as you.
There are also several computer players.
You all play in the same space, and can target, and be targeted by, each other.

Your best score, and cumulative kills are logged and maintained on a leaderboard.
Depending on how well you do, you can rise through the ranks of captains.

You can choose to pick a first name to identify yourself with, and the name of a large (American) city,
or President, to name your ship with.

## Code Structure

The code has a three tier structure: game layer, audio layer, alexa interface.

### Game Layer
This layer of code, contained in the com.tsatsatzu.subwar.game packages, comprises the core
game mechanics. It is accessed, exclusively, through the single entry point in SubWarGameAPI.
This layer is self-contained and persists its own data.

The layer exists so that if another interface is desired, e.g. a web or mobile interface, it can be written on top of
this layer. Those other interfaces would co-exist with this one and the gameplay would be interactive betwen the two.

### Audio Layer
This layer of code, contained in the com.tsatsatzu.subwar.audio packages, comprises the audio interface
to the game. It is accessed, exclusively, through the single entry point in SubWarAudioAPI.
This layer uses the game layer to conduct the game, and its primary responsibility is to convert audio based
commands into game actions, and the results of game actions back into audio messages.

The layer exists so that if another audio interface is desired, e.g. if Google ever do a decent interface to OK Google,
it can be written on top of this layer.

### Alexa Layer
This layer interfaces to the Alexa specific requirements. It translates Alexa intents into calls to the audio API,
and translates reponses from the audio API back to an Alexa response.

## Development Environment

This code was developed with Eclipse Mars and Java 1.8. The AWS plugin was used for Eclipse.
The code project is to be built into a war file and deployed to a web server.
In production this will be a Tomcat 8 server running on an Elastic Beanstalk instance.

