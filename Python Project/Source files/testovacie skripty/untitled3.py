# -*- coding: utf-8 -*-
"""
Created on Wed Apr 27 08:12:15 2022

@author: Adam
"""
userText="Igor"
CelkoveSkore=65
Skore=[userText," ",str(CelkoveSkore)+"\n"]
ListMien=[]
txtzoznam=open("zoznam.txt","a")
txtzoznam.writelines(Skore)
txtzoznam.close()
