# -*- coding: utf-8 -*-
"""
Created on Fri Apr 15 11:51:56 2022

@author: Ady
"""
# jemna skuska čo ako by mohlo byť 
# aj tak to budeš musiet prerobiť ked to budeš davať do okien
import timeit
# budeme porovnavať napisanú vetu so "vzorovou"
veta="Slnko je žlté, Nebo je modré" # test veta 
print(veta)
skore=0 #skóre bude počet znakov vo vete vrate medzier, bodiek a takých hovadin... 
pocetChyb=0
Chyba2=0 # je kvoli tomu elifu
start = timeit.default_timer()
c=input("zadaj vetu : ")
end = timeit.default_timer()
lenC=len(c)
lenVeta=len(veta)
if lenC<lenVeta: # ak nebude rovnako velke pole
    lenCelkove=lenVeta-lenC # toto oprav lebo to nejde ak je pole vascie ako origo-spravene
    # doplňim si tampoceť znakov nejakych random aby mi sedela dlžka poľa
    c=c+lenCelkove*"*" # hvizeda kvoli tomu že ked som tam dal medzeru tak to nesedelo
elif lenVeta<lenC: # ak je pole kt. napiseme mensie ako "vzorove pole"
    Chyba2=lenC-lenVeta # počitam chybu aby som ju nasledne mohol odpocitat zo skoré
for i in range(lenVeta):
    if c[i]==veta[i]: # tu porovnavavame napsi
        skore+=1
    else:
        pocetChyb+=1
pocetChyb+=Chyba2
print(round(end - start,2),"Sekund")
print("Skóre:",skore-Chyba2) #musim odpocitat tu chybu lebo tie polia sa mi nebudu uz sediet
print("Počet chýb: ",pocetChyb)
# To iste skóre za iný čas= vysie miesto v priečke top 5 napr.
# Čas? solved
