# Liferay

-- Tools Used for testing --

TestNG Framework

-- How to execute --

This is a maven project and its enabled to handle configurations for following environments

DEV
INT
PREPROD
PROD

The test can be triggered with the following command

mvn clean test -Denv=[ENVIRONMENT] -Dbrowser=[BROWSER]

In browser such as: CHROME, FIREFOX, OPERA
