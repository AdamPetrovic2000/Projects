# -*- coding: utf-8 -*-
"""
Created on Wed Apr 27 20:23:22 2022

@author: Adam
"""

file=open("TXT/zoznam.txt","r")
zoznam=file.readlines()
zoznam1=file.readline()
file.close()
List=[]
List2=[]
ListFinal=[]
for i in zoznam:
    c=i.split()
    List.append(c)
List=sorted(List, key=lambda x: (x[1]),reverse=True)
file=open("TXT/zoznam.txt","w")
for i in List:
    cele=" ".join(i)
    List2.append(cele)
for i in List2:
    file.write(i+"\n")
file.close()
