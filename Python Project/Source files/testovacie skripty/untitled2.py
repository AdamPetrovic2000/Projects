# -*- coding: utf-8 -*-
"""
Created on Wed Apr 27 06:53:03 2022

@author: Adam
"""

file=open("zoznam.txt","r")
zoznam=file.readlines()
List=[]
for i in zoznam:
    c=i.split()
    List.append(c)
if List==[]:
    pass
elif List[0]!=[] and List[1:4]==[]:
    top1=List[0]
elif List[0:1]!=[] and List[2:4]==[]:
    List=sorted(List, key=lambda x: (x[1]),reverse=True)
    top1=List[0]
    top2=List[1]
elif List[0:2]!=[] and List[3:4]==[]:
    List=sorted(List, key=lambda x: (x[1]),reverse=True)
    top1=List[0]
    top2=List[1]
    top3=List[2]
elif List[0:3]!=[] and List[4:5]==[]:
    List=sorted(List, key=lambda x: (x[1]),reverse=True)
    top1=List[0]
    top2=List[1]
    top3=List[2]
    top4=List[3]
elif List[0:4]!=[] and List[5:5]==[]:
    List=sorted(List, key=lambda x: (x[1]),reverse=True)
    top1=List[0]
    top2=List[1]
    top3=List[2]
    top4=List[3]
    top5=List[4]

    
    

    
    
                


    
