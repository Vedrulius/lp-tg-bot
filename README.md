<h2>Readme</h2>

This is telegram bot for Validator server.

Tested on ubuntu 20.04 and java 11.

This bot can only send messages to your telegram id.

<h3>Installation</h3>

1. Create your personal telegram bot and get Api Token. [Instruction](https://docs.microsoft.com/en-us/azure/bot-service/bot-service-channel-connect-telegram?view=azure-bot-service-4.0)
2. Send to your new bot command /start and go to the next step
3. For now you need to install jre and maven:

        sudo apt update
        sudo apt install default-jre
        sudo apt install maven
        sudo apt install git

You can check version:

    java -version
    mvn -v

4. create directory $HOME/<bot directory>, e.g. mkdir $HOME/bot. 

        cd $HOME/<bot directory>    
        git clone https://github.com/Vedrulius/lp-tg-bot  

5. Go to $HOME/<bot directory>/src/main/resources/ and change file application.properties:
   
        bot.name=<your telegram bot name>
        bot.token=<Api Token>
        bot.id=<your telegram id>, if you don't know your id - google how to get it.

6. Go to $HOME/<bot directory> and run: mvn install -DskipTests
7. Go to $HOME/<bot directory>/target and run: java -jar tgbot.jar