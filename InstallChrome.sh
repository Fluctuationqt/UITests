#!/bin/bash
export DISPLAY=:44
ffmpeg -f x11grab -video_size 1920x1080 -i :44 -codec:v libx264 -r 12 video.mp4
