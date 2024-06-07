# -*- coding: utf-8 -*-
"""
Created on Sun May  1 18:13:10 2022

@author: Adam
"""

VzorVeta = "Toto je veta."
NapisanaVeta = "Toto je vete."
PocetChyb=0
for i in range(len(VzorVeta)):
    if VzorVeta[i]!= NapisanaVeta[i]:
        PocetChyb+=1
print(PocetChyb)        

import time

start = time.perf_counter()
    # niečo spraví #
end = time.perf_counter()

vysCas = end-start