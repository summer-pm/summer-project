#!/bin/sh
#
certbot --nginx -v --domains beercode.ru --agree-tos --email nikitavaskulatov@gmail.com --non-interactive && nginx 


