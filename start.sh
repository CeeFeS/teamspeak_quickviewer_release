while true
do
  sudo java -jar ./backend/tsqv_backend.jar /var/www/html/index.php /home/pi/teamspeak_quickviewer/ressources/standard.css /home/pi/teamspeak_quickviewer/html /home/pi/teamspeak_quickviewer/backend/config.properties
  sleep 5s
done
