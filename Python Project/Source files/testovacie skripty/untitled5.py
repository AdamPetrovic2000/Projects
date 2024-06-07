# -*- coding: utf-8 -*-
"""
Created on Wed Apr 27 11:08:09 2022

@author: Adam
"""

import pygame
pygame.mixer.init()
pygame.mixer.music.load("sound.wav")
pygame.mixer.music.set_volume(0.1) 
pygame.mixer.music.play()