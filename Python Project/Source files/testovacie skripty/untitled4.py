# -*- coding: utf-8 -*-
"""
Created on Wed Apr 27 08:27:07 2022

@author: Adam
"""

file=open("zoznam.txt","r")
zoznam=file.readlines()
List=[]
for i in zoznam:
    c=i.split()
    List.append(c)
if List[1:1]==[]:
    pass
elif List[1]!=[] and List[2:5]==[]:
    top1=List[1]
elif List[1:2]!=[] and List[3:5]==[]:
    List=sorted(List, key=lambda x: (x[1]),reverse=True)
    top1=List[1]
    top2=List[2]
elif List[1:3]!=[] and List[4:5]==[]:
    List=sorted(List, key=lambda x: (x[1]),reverse=True)
    top1=List[1]
    top2=List[2]
    top3=List[3]
    print(top1)
    print(top2)
    print(top3)
elif List[1:4]!=[] and List[5:5]==[]:
    List=sorted(List, key=lambda x: (x[1]),reverse=True)
    top1=List[1]
    top2=List[2]
    top3=List[3]
    top4=List[4]
elif List[1:5]!=[] and List[6:6]==[]:
    List=sorted(List, key=lambda x: (x[1]),reverse=True)
    top1=List[0]
    top2=List[1]
    top3=List[2]
    top4=List[3]
    top5=List[4]
file.close()