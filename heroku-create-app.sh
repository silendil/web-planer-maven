#!/usr/bin/env sh

heroku apps:create web-planer
heroku addons:create heroku-postgresql:hobby-dev --app web-planer