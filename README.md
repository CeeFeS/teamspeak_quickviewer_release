# Teamspeak Quickviewer

Teamspeak Quickviewer is a simple way to host your own Teamspeak Viewer on Linux Server. The frontend is running on an Apache Server with PHP and the backend was developed on Java, Bash- Scripts and HTML. Coded in a structured and understandable way, the project is perfect for expanding the possibilities and adapting the functions to your needs.

## Requirements
- at least: Java version 1.8
- Webserver with PHP

## Installation
1. `wget https://github.com/CeeFeS/teamspeak_quickviewer_release/raw/master/releases/release_v1.27_public.zip`

2. `unzip release_v1.27_public.zip`


## Start

- add the correct TS Query password and username in `backend/config.properties`

- configure the server and datat-points in `start.sh` for your needs.

- add content in `html/{position}.html`

- start the service with `sudo bash start.sh`

## Note

Source code for Java backend: https://github.com/CeeFeS/teamspeak_quickviewer_release/blob/master/tsqv_backend/src/de/ts/cfschulze/Main.java
