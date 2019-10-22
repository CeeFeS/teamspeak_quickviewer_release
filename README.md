# Teamspeak Quickviewer

Teamspeak Quickviewer is a simple way to Host your own Teamspeak Viewer on Linux Server. The Forntend is running on a Apache Server with PHP and the Backend was developed on Java, Bash- Scripts and HTML. Coded in a structured and understandable way the project is perfect for expanding the possibilities and adapting the functions to your needs.

## Requirements
- at least: Java version 1.8
- Webserver with PHP

## Installation
 `wget https://github.com/CeeFeS/teamspeak_quickviewer_release/raw/master/releases/release_v1.13_public.zip`
 `unzip release_v1.13_public.zip`


## Start

Add the correct TS Query password and username in `backend/sq_connect.sh`

Configure the server in `start.sh` for your needs.

Add content in `html/{position}.html`

Start the serice with `sudo bash start.sh`

## Note

Source code for Java backend: https://github.com/CeeFeS/teamspeak_quickviewer_release/blob/master/tsqv_backend/src/de/ts/cfschulze/Main.java
