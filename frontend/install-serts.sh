#!/bin/sh
nginx 
certbot --nginx -v --domains localhost --agree-tos --email nikitavaskulatov@gmail.com --non-interactive
