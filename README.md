This is telegram bot for Validator server.
Tested on ubuntu 20.04 and java 11
This bot can only send messages to your telegram id.
Create your personal telegram bot and get Api Token. [Instruction]:(https://docs.microsoft.com/en-us/azure/bot-service/bot-service-channel-connect-telegram?view=azure-bot-service-4.0)
Send to your new bot command /start and go to the next step

For now you need to install jre and maven:
    sudo apt update
    sudo apt install default-jre
    sudo apt install maven
    sudo apt install git

You can check version
java -version
mvn -v

create directory /bot or whatever you like. 
cd bot
git clone https://github.com/Vedrulius/lp-tg-bot  
