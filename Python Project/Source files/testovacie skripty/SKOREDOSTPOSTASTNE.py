# -*- coding: utf-8 -*-
"""
Created on Sun Apr 24 19:12:07 2022

@author: Ady
"""
"""
x = {"Adam": 40, "Peter": 37, "Jozef": 24}
c={k: v for k, v in sorted(x.items(), key=lambda item: item[1],reverse=True)}
print(c[0])
"""
zoznam=[]
zoznam = [[35,15,"Adam"],[36,15,"Peter"],[31,15,"Karol"],[31,16,"mojzes"],[31,16,"mojzes"],[31,16,"mojzes"]]
zoznam2 = [[57,-18,"Adam"],[57,-16,"Peter"],[25,15,"Karol"],[33,16,"mojzes"]]
f=open("SKORE.txt","r")
hod1=40
hod2=9.653*(-1)
meno="Marek"
M1=f.readline()
M2=f.readline()
M3=f.readline()
M4=f.readline()
M5=f.readline()
M1=M1.split()
M2=M2.split()
M3=M3.split()
M4=M4.split()
M5=M5.split()

M1[1],M1[2]=int(M1[1]),float(M1[2])*(-1)
M2[1],M2[2]=int(M2[1]),float(M2[2])*(-1)
M3[1],M3[2]=int(M3[1]),float(M3[2])*(-1)
M4[1],M4[2]=int(M4[1]),float(M4[2])*(-1)
M5[1],M5[2]=int(M5[1]),float(M5[2])*(-1)
zoznam[0]=M1
zoznam[1]=M2
zoznam[2]=M3
zoznam[3]=M4
zoznam[4]=M5
zoznam[5]=[meno,hod1,hod2]
#a = sorted(zoznam, key=lambda x: x[1:2])
zoznam1=[]
zoznam1=sorted(zoznam, key=lambda x:(x[1],x[2]))

#
f=open("SKORE.txt","w")
zoznam11=zoznam1[0]
zoznam12=zoznam1[1]
zoznam13=zoznam1[2]
zoznam14=zoznam1[3]
zoznam15=zoznam1[4]
zoznam16=zoznam1[5]

zoznam11[1]=str(zoznam11[1])
zoznam12[1]=str(zoznam12[1])
zoznam13[1]=str(zoznam13[1])
zoznam14[1]=str(zoznam14[1])
zoznam15[1]=str(zoznam15[1])
zoznam16[1]=str(zoznam16[1])

zoznam11[2]=str(zoznam11[2])
zoznam12[2]=str(zoznam12[2])
zoznam13[2]=str(zoznam13[2])
zoznam14[2]=str(zoznam14[2])
zoznam15[2]=str(zoznam15[2])
zoznam16[2]=str(zoznam16[2])

str1=" ".join(zoznam16)
str2=" ".join(zoznam15)
str3=" ".join(zoznam14)
str4=" ".join(zoznam13)
str5=" ".join(zoznam12)
writetext=str1+"\n"+str2+"\n"+str3+"\n"+str4+"\n"+str5
f.write(writetext)
f.close()