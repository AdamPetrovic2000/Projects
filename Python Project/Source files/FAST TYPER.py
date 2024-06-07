# -*- coding: utf-8 -*-
"""
Created on Mon Apr 18 11:10:43 2022

@author: Ady
"""
import pygame,sys,time,random,codecs
###########################
#premene
size=(1100,550) # velkosť okna
clock=pygame.time.Clock() 
pygame.init() # inicialujeme hru
pygame.mixer.init() # inicialujeme hudbu
pygame.display.set_caption("FAST TYPER") # nazov okna
win=pygame.display.set_mode((size)) # vykreslovanie okna
fontMenu=pygame.font.SysFont(None,40) # menu font
buttonColor=(120,120,120)
buttonTextColor=(255,255,255)
click=False
pygame.mixer.music.load("hudba/sound.wav")
pygame.mixer.music.play(-1)
hudba=open("TXT/hudba.txt","r")
hudba=hudba.readline()
soundlevel=hudba
pygame.mixer.music.set_volume(int(soundlevel)/1000) 

def menu():
    while True:
        win=pygame.display.set_mode((size))
        fontbutton=pygame.font.SysFont("Arial Black",28)
        fontNadpis=pygame.font.SysFont("Arial Black",60) # menu font
        click=False
        win.fill((255,255,255)) # menu farba
        background=pygame.image.load("IMG/Menu_BG.jpg")
        background=pygame.transform.scale(background,(1100,550))
        win.blit(background,(0,0))
        
        text= fontNadpis.render("FAST TYPER", True,(0,0,0)) # menu nazov a farba
        win.blit(text, (20,30)) # vykreslovanie
        
        
        
        MysX,MysY=pygame.mouse.get_pos() # ziskavame pozicie myše
        
        # buttons:
        button1 = pygame.Rect(50,150,200,50) # vytvarame rect
        button2 = pygame.Rect(50,250,200,50)
        button3 = pygame.Rect(50,350,200,50)
       
        pygame.draw.rect(win,(buttonColor),button1) # kreslime rect
        pygame.draw.rect(win,(buttonColor),button2)
        pygame.draw.rect(win,(buttonColor),button3)
    
        
                                                             # nakreslit rect a az potom nadneho pisať
        textbutton1 = fontbutton.render("PLAY", True, buttonTextColor) # pisanie do rect ale musis najprv 
        win.blit(textbutton1,(110,155)) # suradnice potom doriešit hlavne že to ide 
        
        textbutton2 = fontbutton.render("SETTINGS", True, buttonTextColor)
        win.blit(textbutton2,(70,255))
        
        textbutton3 = fontbutton.render("TOP SKÓRE", True, buttonTextColor)
        win.blit(textbutton3,(60,355))
        
        # cyklus hry
        
        for event in pygame.event.get():
            if event.type == pygame.QUIT: # vypnutie cez X
                pygame.quit()
                sys.exit()
            if event.type == pygame.KEYDOWN:
                if event.key== pygame.K_ESCAPE: # vypnutie cez ESC
                    pygame.quit()
                    sys.exit()
            if event.type ==pygame.MOUSEBUTTONDOWN:
                if event.button == 1:
                    click= True
            
            # stlacenie buttonov
            
            if button1.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    lvl1() # zavolame si tu danu funckiu ktoru sme vytvoril
            if button2.collidepoint(MysX,MysY):
                if click:
                    settings() # volame funckiu a tam si potom daš co budeš chciet dať do options
            if button3.collidepoint(MysX,MysY):
                if click:
                    topskore() # volame funckiu a tam si potom daš co budeš chciet dať do options
            
            pygame.display.update() # updatujeme
            clock.tick(60) # "FPS"
##########################################    TOPSKORE      ############################################################################################
def settings():
    run=True # ak je true pobeží ked tak kukni to video pre vysveltenie
    click=False
    win=pygame.display.set_mode((400,450))
    fontNadpis=pygame.font.SysFont("Arial Black",50)
    fontlevel=pygame.font.SysFont("Arial Black",40)
    file=open("TXT/hudba.txt","r")
    soundlevel=file.readline()
    soundlevel=int(soundlevel)
    file.close()
    soundlevel=round(soundlevel)
    file=open("TXT/obtiaznost.txt","r")
    obtiaznost=file.readline()
    while run:
        
        click=False
        win.fill((0,100,255)) # zmenin si farbu
        
        buttonminus = pygame.Rect(50,165,30,30) # vytvarame rect
        buttonplus = pygame.Rect(250,165,30,30)
        display= pygame.Rect(100,150,135,60)
        buttonleft1= pygame.Rect(50,325,30,30)
        buttonright1=pygame.Rect(250,325,30,30)
        
        pygame.draw.rect(win,(255,255,255),buttonplus) # kreslime rect
        pygame.draw.rect(win,(255,255,255),buttonminus)
        pygame.draw.rect(win,(255,255,255),display)
        pygame.draw.rect(win,(255,255,255),buttonleft1)
        pygame.draw.rect(win,(255,255,255),buttonright1)
        
        
        background=pygame.image.load("IMG/settings_BG.jpg")
        background=pygame.transform.scale(background,(400,450))
        win.blit(background,(0,0))
        
        
        MysX,MysY=pygame.mouse.get_pos()
        
        
        text= fontNadpis.render("Settings", True,(255, 255, 255)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(text, (5,1)) # vykreslovanie
        
        musicleveltxt= fontMenu.render("Music Level: ", True,(255, 255, 255)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(musicleveltxt, (5,80)) # vykreslovanie
        
        #buttonminus = pygame.Rect(50,165,30,30) # vytvarame rect
        #buttonplus = pygame.Rect(250,165,30,30)
        
        
        #pygame.draw.rect(win,(255,255,255),buttonplus) # kreslime rect
        #pygame.draw.rect(win,(255,255,255),buttonminus)
        pygame.draw.rect(win,(255,255,255),display)
        #pygame.draw.rect(win,(255,255,255),buttonleft)
        #pygame.draw.rect(win,(255,255,255),buttonright)
        
        #minus= fontNadpis.render("-", True,(255,0, 0)) # menu nazov a farba to iste vykreslujeme nazov
        #win.blit(minus, (70,137)) # vykreslovanie
        
        #plus= fontNadpis.render("+", True,(255, 0, 0)) # menu nazov a farba to iste vykreslujeme nazov
        #win.blit(plus, (248,142)) # vykreslovanie
        
        buttonplus1=pygame.image.load("IMG/plus.jpg")
        buttonplus1=pygame.transform.scale(buttonplus1,(30,30))
        win.blit(buttonplus1,(50,165))
        
        buttonminus1=pygame.image.load("IMG/minus.jpg")
        buttonminus1=pygame.transform.scale(buttonminus1,(30,30))
        win.blit(buttonminus1,(250,165))
        
        plus= fontMenu.render("Obtiažnosť: ", True,(255,255, 255)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(plus, (5,240)) # vykreslovanie
        
        display2= pygame.Rect(100,300,135,60)
        pygame.draw.rect(win,(255,255,255),display2)
        
        buttonleft=pygame.image.load("IMG/left.jpg")
        buttonleft=pygame.transform.scale(buttonleft,(30,30))
        win.blit(buttonleft,(50,325))
        
        buttonright=pygame.image.load("IMG/right.jpg")
        buttonright=pygame.transform.scale(buttonright,(30,30))
        win.blit(buttonright,(250,325))
        
        
        
        for event in pygame.event.get():
            if event.type == pygame.QUIT: # vypnutie cez X
                pygame.quit()
                sys.exit()
            if event.type == pygame.KEYDOWN:
                if event.key== pygame.K_ESCAPE: # vypnutie cez ESC
                    run=False
                    file=open("TXT/hudba.txt","w")
                    file.write(str(soundlevel))
                    file.close()        # ak dame ESC vrati sa naspať do menu
            if event.type ==pygame.MOUSEBUTTONDOWN:
                if event.button == 1:
                    click= True
            if buttonplus.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    soundlevel+=5
                    
            if buttonminus.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    soundlevel-=5
            if buttonleft1.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    if obtiaznost=="Hard":
                        obtiaznost="Easy"
                    elif obtiaznost=="Easy":
                        obtiaznost="Hard"
            if buttonright1.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    if obtiaznost=="Hard":
                        obtiaznost="Easy"
                    elif obtiaznost=="Easy":
                        obtiaznost="Hard"
        
        obtiaznosttxt= fontlevel.render(obtiaznost, True,(0, 0, 0)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(obtiaznosttxt, (115,300)) # vykreslovanie            
        
        file=open("TXT/obtiaznost.txt","w") # zapišeš do suboru a prečitaš potom uz v leveli 
        file.write(obtiaznost)
                    
        if soundlevel>100: # zabezepečujeme aby nebolo vaascie ako 100 a menšie ako nula
            soundlevel=100
        elif soundlevel<0:
            soundlevel=0
        else:
            soundlevel=soundlevel
            
        Musicleveltxt1= fontlevel.render(str(soundlevel)+"%", True,(0, 0, 0)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(Musicleveltxt1, (115,150)) # vykreslovanie            
        pygame.mixer.music.set_volume(soundlevel/1000)           
                    
                    
        pygame.display.update() #  zase updatujeme lebo osobitny cyklus pre danu hru
        clock.tick(60) # "FPS"
        
##########################################    SETTINGS     ############################################################################################

##########################################    TOPSKORE      ############################################################################################
def topskore():
    #win=pygame.display.set_mode((500,500))
    run=True # ak je true pobeží ked tak kukni to video pre vysveltenie
    #size=(500,500)
    win=pygame.display.set_mode((500,500))
    fontNadpis=pygame.font.SysFont("Arial Black",60)
    fontMeno=pygame.font.SysFont("Arial",35)
    fontMiesto=pygame.font.SysFont("Arial Black",35)
    
    file=codecs.open("TXT/zoznam.txt","r","utf-8")
    zoznam=file.readlines()
    file.close()
    List=[]
    List2=[]
    for i in zoznam:
        c=i.split()
        List.append(c)
    List=sorted(List, key=lambda x: (x[1]),reverse=True)
    file=codecs.open("TXT/zoznam.txt","w","utf-8")
    for i in List:
        cele="      ".join(i)
        List2.append(cele)
    for i in List2:
        file.write(i+"\n")
    file.close()
    file=codecs.open("TXT/zoznam.txt","r","utf-8")
    top1=file.readline()
    top1=top1.strip()
    #top1=top1.split()
    
    top2=file.readline()
    top2=top2.strip()
    #top2=top2.split()
    
    top3=file.readline()
    top3=top3.strip()
    #top3=top3.split()
    
    top4=file.readline()
    top4=top4.strip()
    #top4=top4.split()
    
    top5=file.readline()
    top5=top5.strip()
    #top5=top5.split()
    """
    if zoznam1=="":
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
    """
    
    while run:
        win.fill((0,127,127)) # zmenin si farbu
        
        background=pygame.image.load("IMG/skore_BG.jpg")
        background=pygame.transform.scale(background,(500,500))
        win.blit(background,(0,0))
        #NAZOV
        text= fontNadpis.render("TOP SKÓRE", True,(255, 0, 0)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(text, (45,0)) # vykreslovanie
        
        
       
        #textmeno= fontMiesto.render("Hráč :", True,(0, 0, 0)) # menu nazov a farba to iste vykreslujeme nazov
        #win.blit(textmeno, (100,75)) # vykreslovanie
        
        #textskore= fontMiesto.render("Skóre :", True,(0, 0, 0)) # menu nazov a farba to iste vykreslujeme nazov
        #win.blit(textskore, (300,75)) # vykreslovanie
        
       
        #PRVE MIESTO
       
        
        
        text1= fontMiesto.render("1)", True,(0, 0, 0)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(text1, (10,145)) # vykreslovanie
        
        textmeno1= fontMeno.render(top1, True,(0, 0, 255)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(textmeno1, (100,150)) # vykreslovanie
        
        #textskore1= fontMeno.render(top1[1], True,(0, 0, 255)) # menu nazov a farba to iste vykreslujeme nazov
        #win.blit(textskore1, (300,150)) # vykreslovanie
        
        #DRUHE MIESTO
     
        
        
        text1= fontMiesto.render("2)", True,(0, 0, 0)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(text1, (10,195)) # vykreslovanie
        
        textmeno1= fontMeno.render(top2, True,(0, 0, 255)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(textmeno1, (100,200)) # vykreslovanie
        
        #textskore1= fontMeno.render(top2[1], True,(0, 0, 255)) # menu nazov a farba to iste vykreslujeme nazov
        #win.blit(textskore1, (300,200)) # vykreslovanie
        
        #3 MIESTO
     
        
        
        text1= fontMiesto.render("3)", True,(0, 0, 0)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(text1, (10,245)) # vykreslovanie
        
        textmeno1= fontMeno.render(top3, True,(0, 0, 255)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(textmeno1, (100,250)) # vykreslovanie
        
        #textskore1= fontMeno.render(top3[1], True,(0, 0, 255)) # menu nazov a farba to iste vykreslujeme nazov
        #win.blit(textskore1, (300,250)) # vykreslovanie
        
        #4 MIESTO
      
        
        
        text1= fontMiesto.render("4)", True,(0, 0, 0)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(text1, (10,295)) # vykreslovanie
        
        textmeno1= fontMeno.render(top4, True,(0, 0, 255)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(textmeno1, (100,300)) # vykreslovanie
        
        #textskore1= fontMeno.render(top4[1], True,(0, 0, 255)) # menu nazov a farba to iste vykreslujeme nazov
        #win.blit(textskore1, (300,300)) # vykreslovanie
        
        #5 MIESTO
        
        
        text1= fontMiesto.render("5)", True,(0, 0, 0)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(text1, (10,345)) # vykreslovanie
        
        textmeno1= fontMeno.render(top5, True,(0, 0, 255)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(textmeno1, (100,350)) # vykreslovanie
        
        #textskore1= fontMeno.render(top5[1], True,(0, 0, 255)) # menu nazov a farba to iste vykreslujeme nazov
        #win.blit(textskore1, (300,350)) # vykreslovanie
        
        
        
        
        
        for event in pygame.event.get():
            if event.type == pygame.QUIT: # vypnutie cez X
                pygame.quit()
                sys.exit()
            if event.type == pygame.KEYDOWN:
                if event.key== pygame.K_ESCAPE: # vypnutie cez ESC
                    run=False  # ak dame ESC vrati sa naspať do menu 
                    #win=pygame.display.set_mode((1000,1000))
        
        
        
        pygame.display.update() #  zase updatujeme lebo osobitny cyklus pre danu hru
        clock.tick(60) # "FPS"
        
        ##########################################    TOPSKORE      ###################################################
        
 ##########################################    LVL1       #################################################################################################################################              
def lvl1():# cyklus na samotnu hru  toto iste budeš použivat aj pre ostatne čo tam daš ako top skore atď .... 
    win=pygame.display.set_mode((1100,550))    
    run=True # ak je true pobeží ked tak kukni to video pre vysveltenie
    fontNadpis=pygame.font.SysFont("Arial Black",32)
    fontStats=pygame.font.SysFont("calibri",40)
    font=pygame.font.SysFont("Calibri Light",32)
    vysfarba=(255,255,255)
    veta=""
    veta1=vetylvl1() # toto budes meniť v každom levely #################################################################################################################################################
    for i in range(len(veta1)-1): # toto je kvoli tomu že na konci sa robi nejaky neznamy znak a neviem aky tak takto inak neviem replace nefunguje lebo nepoznam znak ani join mi nejak nejde
        veta+=veta1[i]
    lenveta=len(veta1)-1
    maxskore=10 # aj toto budeš meniť pri každom levely #################################################################################################################################################
    
    end=0
    start=0
    userText=""
    cas1=0 # pomocna
    pocetStlacKlaves=0 # pomocna pre stalečenie klaves
    Chyby=0 # premenna na chyby 
    dlzkavety=0 # pomocna pre vety
    pocetpresnost=0
    # namiesto tohto daš button asi naskor....
    stlacStart=0 # zamedzujeme crashu
    cas=[""] # kdyby neco davam to listu lebo to potrebujem uložit z iteracie!!!! nezabudni
    pocetznakov=[""]
    chyby=[""] # toto je ten isty prípad
    chybatext=""
    chybaveta=""
    skore=[""]# skóre je počet znakov vo vete minus pocet chyb
    presnost=[""]
    Starkey="PRESS STARTBUTTON TO START!"
    ukazstats=0
    x,y,w,h=0,0,0,0
    x1,y1,w1,h1=0,0,0,0
    while run:
        nextlevelbutton=pygame.Rect(x,y,w,h)
        pygame.draw.rect(win,(255,255,255),nextlevelbutton)
        restartButton = pygame.Rect(x1,y1,w1,h1)
        pygame.draw.rect(win,(0,0,0),restartButton)
        click=False
        win.fill((0,150,255))
        background=pygame.image.load("IMG/game_BG.jpg")
        background=pygame.transform.scale(background,(1100,700))
        win.blit(background,(0,0))
        chybatext=userText
        chybaveta=veta
        # mozem menit aj farbu 
        #text= font.render("game.... WIP", True,(255, 255, 255)) # menu nazov a farba to iste vykreslujeme nazov ### toto bolo iba pre ukažku
        #win.blit(text, (1,1)) # vykreslovanie
        startkey=font.render(Starkey,True,(255,255,255)) # podstatne vypisujeme vzorovú vetu ktorú nam hore 
        win.blit(startkey,(350,300))
        textlevel=fontNadpis.render("LEVEL 1",True,(255,255,255))
        win.blit(textlevel,(940,0))
        if stlacStart==1:
            Starkey=""
            vetaNadpis=fontNadpis.render("VETA:",True,(255,255,0))
            win.blit(vetaNadpis,(0,250))
            vetatext = pygame.Rect(5,300,1090,50)
            pygame.draw.rect(win,(80,80,80),vetatext)
            vetavzor=font.render(veta,True,(255,255,255)) # podstatne vypisujeme vzorovú vetu ktorú nam hore 
            win.blit(vetavzor,(5,315))
        elif stlacStart==0:
            Starkey="PRESS START BUTTON TO START!"
            win.blit(startkey,(350,300))
        
        MysX,MysY=pygame.mouse.get_pos()
        
        wintext = pygame.Rect(5,425,1090,50)
        pygame.draw.rect(win,(255,255,255),wintext)
        
        startButton = pygame.Rect(5,485,150,50)
        pygame.draw.rect(win,(0,0,0),startButton)
        StartBut=pygame.image.load("IMG/StartButton.png")
        StartBut=pygame.transform.scale(StartBut,(150,50))
        win.blit(StartBut,(5,485))
        """
        restartButton = pygame.Rect(160,485,150,50)
        pygame.draw.rect(win,(0,0,0),restartButton)  ###########toto uz nebudeš potrebovať 
        restartBut=pygame.image.load("RestartButton.png")
        restartBut=pygame.transform.scale(restartBut,(150,50))
        win.blit(restartBut,(160,485))
        """
        for event in pygame.event.get():
            if event.type == pygame.QUIT: # vypnutie cez X
                pygame.quit()
                sys.exit()
                stlacStart=0
            if event.type ==pygame.MOUSEBUTTONDOWN:
                if event.button == 1:
                    click= True
            if startButton.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    userText=""
                    stlacStart=1
                    # ošetruejeme aby sa všetko vynoloval pokial niesu zapnute stopky
                    start=time.perf_counter() # začiname pocitat čas
            if nextlevelbutton.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    lvl2()
                    #pygame.quit() # toto musis potom zmeniť ked to budeš davat to menu skriptu lebo teraz to mas aby ti to fungovalo mimo skriput
                    #sys.exit() #################################################################################################################################################
            if restartButton.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    userText=""
                    # ošetruejeme aby sa všetko vynoloval pokial niesu zapnute stopky
                    start=time.perf_counter() # začiname pocitat čas
                    stlacStart=0
                    chyby[0]=""
                    pocetznakov[0]=""      # toto použižeš aj na rešart levelu
                    cas[0]=""
                    skore[0]=""
                    pocetStlacKlaves=0
                    pocetpresnost=0
                    presnost[0]=""
                    ukazstats=0
            
                    
            if event.type == pygame.KEYDOWN:
                pocetStlacKlaves+=1 #pocet stlačených klaves
                if event.key== pygame.K_ESCAPE: # vypnutie cez ESC
                     run=False # toto musis potom zmeniť ked to budeš davat to menu skriptu lebo teraz to mas aby ti to fungovalo mimo skriput
                         # aj ten run musis dať aby ti to pootom išlo
                elif event.key == pygame.K_BACKSPACE:
                    userText = userText[0:-1]
                elif event.key == pygame.K_TAB:    
                    vysSkore() # toto su cheaty :D # daš potom preč to pre kontrolu posledneho okna na skóre 
                elif event.key == pygame.K_RETURN and stlacStart==1:
                    ukazstats=1
                    if (len(chybatext))<len(chybaveta):
                      dlzkavety=(len(chybaveta)-len(chybatext)) # chybou text to nahradzuješ aby neprepisalo vetu ktoru pišeš
                      chybatext= chybatext+(dlzkavety*"*")
                    elif (len(chybatext))>len(chybaveta):
                        dlzkavety=(len(chybatext)-len(chybaveta)) # ak
                        chybaveta= chybaveta+(dlzkavety*"*")
                    for i in range (len(chybatext)): # určujem chybu ale zatial iba ked su rovnako velké
                        if chybatext[i]!=chybaveta[i]:
                            Chyby+=1
                        elif chybatext[i]== chybaveta[i] :
                            pocetpresnost+=1
                    if Chyby>maxskore:
                        Chyby=maxskore
                    
                    
                    
    
                    ##################### premene pre čas chybu atď....        
                    skore[0]= str(maxskore-Chyby)
                    presnost[0]=round((pocetpresnost/lenveta)*100)
                    presnost[0]=str(presnost[0])+"%"
                    chyby[0]=str(Chyby)
                    end=time.perf_counter() # tu končíme počitanie času
                    cas1=round(end-start,2)
                    cas[0]=round(end-start,2)
                    cas[0]=str(cas[0])+" sek"
                    if (pocetStlacKlaves-2)<=0: # toto budes musiet potom zmeniť iba na -1 lebo keď tam daš button a potom všade
                        pocetznakov[0]="0 u/s"
                    else:
                        pocetznakov[0]=round(((pocetStlacKlaves-2)/cas1),2)  # toto je vypočet počet uderov za čas
                        pocetznakov[0]= str(pocetznakov[0])+" u/s" # takto pridaš jednotku k hodnote
                     
                    txtskore=open("TXT/tempskore.txt","w")
                    txtskore.write(skore[0])
                    txtskore.close()
                    
                    txtcas=open("TXT/tempcas.txt","w")
                    txtcas.write(str(round(end-start,2)))
                    txtcas.close()
                else: # pisanie 
                    userText += event.unicode # pišeš pridavaš do stringu
            
                        
        if ukazstats==1:
            textStats=fontNadpis.render("STATS:",True,(vysfarba))
            win.blit(textStats,(0,0))
            
            textskore = fontNadpis.render("SKÓRE: "+skore[0],True,(255,0,0)) # vyoisujeme skóre
            win.blit(textskore,(460,200))
        
            textpresnost = fontStats.render("presnosť: "+presnost[0],True,vysfarba) # vyoisujeme presnosť 
            win.blit(textpresnost,(0,150))
        
            textrychlost = fontStats.render("rýchlosť: "+pocetznakov[0],True,vysfarba) # vyoisujeme chybu 
            win.blit(textrychlost,(0,200))
        
            textchyby = fontStats.render("počet chýb: "+chyby[0],True,vysfarba) # vyoisujeme chybu 
            win.blit(textchyby,(0,100))
        
            textcas = fontStats.render("čas: "+cas[0],True,vysfarba) # vypisujeme čas
            win.blit(textcas,(0,50))
            
            x,y,w,h = (940,485,150,50) # button na next level vykreslenie
            
            NextlevelBut=pygame.image.load("IMG/nextLevel.jpg")
            NextlevelBut=pygame.transform.scale(NextlevelBut,(150,50))
            win.blit(NextlevelBut,(940,485))
            #pygame.draw.rect(win,(255,255,255),nextlevelbutton)
            #textlevel = font.render("čas:",True,vysfarba) # vypisujeme čas # toto uz tiež nepotrebuješ
            #win.blit(nextlevelbutton,(1000,100))
            
            
            x1,y1,w1,h1=(160,485,150,50)
            restartBut=pygame.image.load("IMG/RestartButton.png")
            restartBut=pygame.transform.scale(restartBut,(150,50))
            win.blit(restartBut,(160,485))
            
            
            #########################
        
        text = font.render(userText,True,(0,0,0))
        win.blit(text,(5,440))

        
        
        
        pygame.display.update() #  zase updatujeme lebo osobitny cyklus pre danu hru
        clock.tick(60) # "FPS"

def vetylvl1(): # takúto funkciu pre každy lvl ak bude aj obtiažnost tak 2..n fuknci pre dany level zaleží kolko obtiažnosti bude
    cislo=""
    file=open("TXT/obtiaznost.txt","r")
    obtiaznost=file.readline()
    if obtiaznost=="Easy":
        f=open("LEVEL/LVL1.txt","r")
    else:
        f=open("LEVEL/H_LVL1.txt","r")
    cislo=random.randint(0,2)
    if cislo==0:
        return f.readline()
    elif cislo==1:
        f.readline() 
        return f.readline()
    elif cislo==2:
        f.readline()
        f.readline()    
        return f.readline()
 #####################################################################################     LEVEL 1  ################################################################################################################################# LVL1

########################################################################################## LEVEL 2############################################################################################
def lvl2():
    win=pygame.display.set_mode((1100,550))
    run=True # ak je true pobeží ked tak kukni to video pre vysveltenie
    fontNadpis=pygame.font.SysFont("Arial Black",32)
    fontStats=pygame.font.SysFont("calibri",40)
    font=pygame.font.SysFont("Calibri Light",32)
    vysfarba=(255,255,255)
    veta=""
    veta1=vetylvl2() # toto budes meniť v každom levely #################################################################################################################################################
    for i in range(len(veta1)-1): # toto je kvoli tomu že na konci sa robi nejaky neznamy znak a neviem aky tak takto inak neviem replace nefunguje lebo nepoznam znak ani join mi nejak nejde
        veta+=veta1[i]
    lenveta=len(veta1)-1
    maxskore=10 # aj toto budeš meniť pri každom levely #################################################################################################################################################
    
    end=0
    start=0
    userText=""
    cas1=0 # pomocna
    pocetStlacKlaves=0 # pomocna pre stalečenie klaves
    Chyby=0 # premenna na chyby 
    dlzkavety=0 # pomocna pre vety
    pocetpresnost=0
    # namiesto tohto daš button asi naskor....
    stlacStart=0 # zamedzujeme crashu
    cas=[""] # kdyby neco davam to listu lebo to potrebujem uložit z iteracie!!!! nezabudni
    pocetznakov=[""]
    chyby=[""] # toto je ten isty prípad
    chybatext=""
    chybaveta=""
    skore=[""]# skóre je počet znakov vo vete minus pocet chyb
    presnost=[""]
    Starkey="PRESS STARTBUTTON TO START!"
    ukazstats=0
    x,y,w,h=0,0,0,0
    x1,y1,w1,h1=0,0,0,0
    while run:
        nextlevelbutton=pygame.Rect(x,y,w,h)
        pygame.draw.rect(win,(255,255,255),nextlevelbutton)
        restartButton = pygame.Rect(x1,y1,w1,h1)
        pygame.draw.rect(win,(0,0,0),restartButton)
        click=False
        win.fill((0,150,255))
        background=pygame.image.load("IMG/game_BG.jpg")
        background=pygame.transform.scale(background,(1100,700))
        win.blit(background,(0,0))
        chybatext=userText
        chybaveta=veta
        # mozem menit aj farbu 
        #text= font.render("game.... WIP", True,(255, 255, 255)) # menu nazov a farba to iste vykreslujeme nazov ### toto bolo iba pre ukažku
        #win.blit(text, (1,1)) # vykreslovanie
        startkey=font.render(Starkey,True,(255,255,255)) # podstatne vypisujeme vzorovú vetu ktorú nam hore 
        win.blit(startkey,(350,300))
        textlevel=fontNadpis.render("LEVEL 2",True,(255,255,255)) #########################################################
        win.blit(textlevel,(940,0))
        if stlacStart==1:
            Starkey=""
            vetaNadpis=fontNadpis.render("VETA:",True,(255,255,0))
            win.blit(vetaNadpis,(0,250))
            vetatext = pygame.Rect(5,300,1090,50)
            pygame.draw.rect(win,(80,80,80),vetatext)
            vetavzor=font.render(veta,True,(255,255,255)) # podstatne vypisujeme vzorovú vetu ktorú nam hore 
            win.blit(vetavzor,(5,315))
        elif stlacStart==0:
            Starkey="PRESS START BUTTON TO START!"
            win.blit(startkey,(350,300))
        
        MysX,MysY=pygame.mouse.get_pos()
        
        wintext = pygame.Rect(5,425,1090,50)
        pygame.draw.rect(win,(255,255,255),wintext)
        
        startButton = pygame.Rect(5,485,150,50)
        pygame.draw.rect(win,(0,0,0),startButton)
        StartBut=pygame.image.load("IMG/StartButton.png")
        StartBut=pygame.transform.scale(StartBut,(150,50))
        win.blit(StartBut,(5,485))
        """
        restartButton = pygame.Rect(160,485,150,50)
        pygame.draw.rect(win,(0,0,0),restartButton)  ###########toto uz nebudeš potrebovať 
        restartBut=pygame.image.load("RestartButton.png")
        restartBut=pygame.transform.scale(restartBut,(150,50))
        win.blit(restartBut,(160,485))
        """
        for event in pygame.event.get():
            if event.type == pygame.QUIT: # vypnutie cez X
                pygame.quit()
                sys.exit()
                stlacStart=0
            if event.type ==pygame.MOUSEBUTTONDOWN:
                if event.button == 1:
                    click= True
            if startButton.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    userText=""
                    stlacStart=1
                    # ošetruejeme aby sa všetko vynoloval pokial niesu zapnute stopky
                    start=time.perf_counter() # začiname pocitat čas
            if nextlevelbutton.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    #pass
                    lvl3()
                    #pygame.quit() # toto musis potom zmeniť ked to budeš davat to menu skriptu lebo teraz to mas aby ti to fungovalo mimo skriput
                    #sys.exit() #################################################################################################################################################
            if restartButton.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    userText=""
                    # ošetruejeme aby sa všetko vynoloval pokial niesu zapnute stopky
                    start=time.perf_counter() # začiname pocitat čas
                    stlacStart=0
                    chyby[0]=""
                    pocetznakov[0]=""      # toto použižeš aj na rešart levelu
                    cas[0]=""
                    skore[0]=""
                    pocetStlacKlaves=0
                    pocetpresnost=0
                    presnost[0]=""
                    ukazstats=0
            
                    
            if event.type == pygame.KEYDOWN:
                pocetStlacKlaves+=1 #pocet stlačených klaves
                if event.key== pygame.K_ESCAPE: # vypnutie cez ESC
                     run=False # toto musis potom zmeniť ked to budeš davat to menu skriptu lebo teraz to mas aby ti to fungovalo mimo skriput
                     menu()      # aj ten run musis dať aby ti to pootom išlo
                elif event.key == pygame.K_BACKSPACE:
                    userText = userText[0:-1]
                elif event.key == pygame.K_RETURN and stlacStart==1:
                    """
                    txtskore=open("TXT/tempskore.txt","w+r")
                    skorelvl=txtskore.readline()
                    skore1=int(skorelvl)+int(skore[0])
                    
                    txtskore.write(skore1)
                    txtskore.close()
                    """
                    
                    
                    ukazstats=1
                    if (len(chybatext))<len(chybaveta):
                      dlzkavety=(len(chybaveta)-len(chybatext)) # chybou text to nahradzuješ aby neprepisalo vetu ktoru pišeš
                      chybatext= chybatext+(dlzkavety*"*")
                    elif (len(chybatext))>len(chybaveta):
                        dlzkavety=(len(chybatext)-len(chybaveta)) # ak
                        chybaveta= chybaveta+(dlzkavety*"*")
                    for i in range (len(chybatext)): # určujem chybu ale zatial iba ked su rovnako velké
                        if chybatext[i]!=chybaveta[i]:
                            Chyby+=1
                        elif chybatext[i]== chybaveta[i] :
                            pocetpresnost+=1
                    if Chyby>maxskore:
                        Chyby=maxskore
                    
                    
                    
    
                    ##################### premene pre čas chybu atď....        
                    skore[0]= str(maxskore-Chyby)
                    presnost[0]=round((pocetpresnost/lenveta)*100)
                    presnost[0]=str(presnost[0])+"%"
                    chyby[0]=str(Chyby)
                    end=time.perf_counter() # tu končíme počitanie času
                    cas1=round(end-start,2)
                    cas[0]=round(end-start,2)
                    cas[0]=str(cas[0])+" sek"
                    if (pocetStlacKlaves-2)<=0: # toto budes musiet potom zmeniť iba na -1 lebo keď tam daš button a potom všade
                        pocetznakov[0]="0 u/s"
                    else:
                        pocetznakov[0]=round(((pocetStlacKlaves-2)/cas1),2)  # toto je vypočet počet uderov za čas
                        pocetznakov[0]= str(pocetznakov[0])+" u/s" # takto pridaš jednotku k hodnote
                    
                    # pocitanie casu a skore do dalšieho levelu
                    txtskore=open("TXT/tempskore.txt","r")
                    skorelvl=txtskore.readline()
                    skore1=int(skorelvl)+int(skore[0])
                    txtskore.close()
                    txtskore=open("TXT/tempskore.txt","w")
                    txtskore.write(str(skore1))
                    txtskore.close()
                    ###################
                    txtcas=open("TXT/tempcas.txt","r")
                    caslvl=txtcas.readline()
                    cas1=float(caslvl)+round(end-start,2)
                    txtskore.close()
                    txtcas=open("TXT/tempcas.txt","w")
                    txtcas.write(str(cas1))
                    txtcas.close()
                    
                
                else: # pisanie 
                    userText += event.unicode # pišeš pridavaš do stringu
            
                        
        if ukazstats==1:
            textStats=fontNadpis.render("STATS:",True,(vysfarba))
            win.blit(textStats,(0,0))
            
            textskore = fontNadpis.render("SKÓRE: "+skore[0],True,(255,0,0)) # vyoisujeme skóre
            win.blit(textskore,(460,200))
        
            textpresnost = fontStats.render("presnosť: "+presnost[0],True,vysfarba) # vyoisujeme presnosť 
            win.blit(textpresnost,(0,150))
        
            textrychlost = fontStats.render("rýchlosť: "+pocetznakov[0],True,vysfarba) # vyoisujeme chybu 
            win.blit(textrychlost,(0,200))
        
            textchyby = fontStats.render("počet chýb: "+chyby[0],True,vysfarba) # vyoisujeme chybu 
            win.blit(textchyby,(0,100))
        
            textcas = fontStats.render("čas: "+cas[0],True,vysfarba) # vypisujeme čas
            win.blit(textcas,(0,50))
            
            x,y,w,h = (940,485,150,50) # button na next level vykreslenie
            
            NextlevelBut=pygame.image.load("IMG/nextLevel.jpg")
            NextlevelBut=pygame.transform.scale(NextlevelBut,(150,50))
            win.blit(NextlevelBut,(940,485))
            #pygame.draw.rect(win,(255,255,255),nextlevelbutton)
            #textlevel = font.render("čas:",True,vysfarba) # vypisujeme čas # toto uz tiež nepotrebuješ
            #win.blit(nextlevelbutton,(1000,100))
            
            
            x1,y1,w1,h1=(160,485,150,50)
            restartBut=pygame.image.load("IMG/RestartButton.png")
            restartBut=pygame.transform.scale(restartBut,(150,50))
            win.blit(restartBut,(160,485))
            
            
        #########################
        text = font.render(userText,True,(0,0,0)) # vypisujeme pisany text   totoo musí byť na konci inak sa to tomu nieco stale nepačí
        win.blit(text,(5,440))
    
        
        
        pygame.display.update() #  zase updatujeme lebo osobitny cyklus pre danu hru
        clock.tick(60) # "FPS"

def vetylvl2(): # takúto funkciu pre každy lvl ak bude aj obtiažnost tak 2..n fuknci pre dany level zaleží kolko obtiažnosti bude
    cislo=""
    file=codecs.open("TXT/obtiaznost.txt","r")
    obtiaznost=file.readline()
    if obtiaznost=="Easy":
        f=open("LEVEL/LVL2.txt","r")
    else:
        f=open("LEVEL/H_LVL2.txt","r")
    cislo=random.randint(0,2)
    if cislo==0:
        return f.readline()
    elif cislo==1:
        f.readline() 
        return f.readline()
    elif cislo==2:
        f.readline()
        f.readline()    
        return f.readline()
 
########################################################################################## LEVEL 2############################################################################################ 

########################################################################################## LEVEL 3 ############################################################################################ 
def lvl3():
    win=pygame.display.set_mode((1100,550))
    run=True # ak je true pobeží ked tak kukni to video pre vysveltenie
    fontNadpis=pygame.font.SysFont("Arial Black",32)
    fontStats=pygame.font.SysFont("calibri",40)
    font=pygame.font.SysFont("Calibri Light",32)
    vysfarba=(255,255,255)
    veta=""
    veta1=vetylvl3() # toto budes meniť v každom levely #################################################################################################################################################
    for i in range(len(veta1)-1): # toto je kvoli tomu že na konci sa robi nejaky neznamy znak a neviem aky tak takto inak neviem replace nefunguje lebo nepoznam znak ani join mi nejak nejde
        veta+=veta1[i]
    lenveta=len(veta1)-1
    maxskore=10 # aj toto budeš meniť pri každom levely #################################################################################################################################################
    
    end=0
    start=0
    userText=""
    cas1=0 # pomocna
    pocetStlacKlaves=0 # pomocna pre stalečenie klaves
    Chyby=0 # premenna na chyby 
    dlzkavety=0 # pomocna pre vety
    pocetpresnost=0
    # namiesto tohto daš button asi naskor....
    stlacStart=0 # zamedzujeme crashu
    cas=[""] # kdyby neco davam to listu lebo to potrebujem uložit z iteracie!!!! nezabudni
    pocetznakov=[""]
    chyby=[""] # toto je ten isty prípad
    chybatext=""
    chybaveta=""
    skore=[""]# skóre je počet znakov vo vete minus pocet chyb
    presnost=[""]
    Starkey="PRESS STARTBUTTON TO START!"
    ukazstats=0
    x,y,w,h=0,0,0,0
    x1,y1,w1,h1=0,0,0,0
    while run:
        nextlevelbutton=pygame.Rect(x,y,w,h)
        pygame.draw.rect(win,(255,255,255),nextlevelbutton)
        restartButton = pygame.Rect(x1,y1,w1,h1)
        pygame.draw.rect(win,(0,0,0),restartButton)
        click=False
        win.fill((0,150,255))
        background=pygame.image.load("IMG/game_BG.jpg")
        background=pygame.transform.scale(background,(1100,700))
        win.blit(background,(0,0))
        chybatext=userText
        chybaveta=veta
        # mozem menit aj farbu 
        #text= font.render("game.... WIP", True,(255, 255, 255)) # menu nazov a farba to iste vykreslujeme nazov ### toto bolo iba pre ukažku
        #win.blit(text, (1,1)) # vykreslovanie
        startkey=font.render(Starkey,True,(255,255,255)) # podstatne vypisujeme vzorovú vetu ktorú nam hore 
        win.blit(startkey,(350,300))
        textlevel=fontNadpis.render("LEVEL 3",True,(255,255,255)) ########################################################################################################################
        win.blit(textlevel,(940,0))
        if stlacStart==1:
            Starkey=""
            vetaNadpis=fontNadpis.render("VETA:",True,(255,255,0))
            win.blit(vetaNadpis,(0,250))
            vetatext = pygame.Rect(5,300,1090,50)
            pygame.draw.rect(win,(80,80,80),vetatext)
            vetavzor=font.render(veta,True,(255,255,255)) # podstatne vypisujeme vzorovú vetu ktorú nam hore 
            win.blit(vetavzor,(5,315))
        elif stlacStart==0:
            Starkey="PRESS START BUTTON TO START!"
            win.blit(startkey,(350,300))
        
        MysX,MysY=pygame.mouse.get_pos()
        
        wintext = pygame.Rect(5,425,1090,50)
        pygame.draw.rect(win,(255,255,255),wintext)
        
        startButton = pygame.Rect(5,485,150,50)
        pygame.draw.rect(win,(0,0,0),startButton)
        StartBut=pygame.image.load("IMG/StartButton.png")
        StartBut=pygame.transform.scale(StartBut,(150,50))
        win.blit(StartBut,(5,485))
        """
        restartButton = pygame.Rect(160,485,150,50)
        pygame.draw.rect(win,(0,0,0),restartButton)  ###########toto uz nebudeš potrebovať 
        restartBut=pygame.image.load("RestartButton.png")
        restartBut=pygame.transform.scale(restartBut,(150,50))
        win.blit(restartBut,(160,485))
        """
        for event in pygame.event.get():
            if event.type == pygame.QUIT: # vypnutie cez X
                pygame.quit()
                sys.exit()
                stlacStart=0
            if event.type ==pygame.MOUSEBUTTONDOWN:
                if event.button == 1:
                    click= True
            if startButton.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    userText=""
                    stlacStart=1
                    # ošetruejeme aby sa všetko vynoloval pokial niesu zapnute stopky
                    start=time.perf_counter() # začiname pocitat čas
            if nextlevelbutton.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    #pass
                    lvl4()
                    #pygame.quit() # toto musis potom zmeniť ked to budeš davat to menu skriptu lebo teraz to mas aby ti to fungovalo mimo skriput
                    #sys.exit() #################################################################################################################################################
            if restartButton.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    userText=""
                    # ošetruejeme aby sa všetko vynoloval pokial niesu zapnute stopky
                    start=time.perf_counter() # začiname pocitat čas
                    stlacStart=0
                    chyby[0]=""
                    pocetznakov[0]=""      # toto použižeš aj na rešart levelu
                    cas[0]=""
                    skore[0]=""
                    pocetStlacKlaves=0
                    pocetpresnost=0
                    presnost[0]=""
                    ukazstats=0
            
                    
            if event.type == pygame.KEYDOWN:
                pocetStlacKlaves+=1 #pocet stlačených klaves
                if event.key== pygame.K_ESCAPE: # vypnutie cez ESC
                     run=False # toto musis potom zmeniť ked to budeš davat to menu skriptu lebo teraz to mas aby ti to fungovalo mimo skriput
                     menu()      # aj ten run musis dať aby ti to pootom išlo
                elif event.key == pygame.K_BACKSPACE:
                    userText = userText[0:-1]
                elif event.key == pygame.K_RETURN and stlacStart==1:
                    ukazstats=1
                    if (len(chybatext))<len(chybaveta):
                      dlzkavety=(len(chybaveta)-len(chybatext)) # chybou text to nahradzuješ aby neprepisalo vetu ktoru pišeš
                      chybatext= chybatext+(dlzkavety*"*")
                    elif (len(chybatext))>len(chybaveta):
                        dlzkavety=(len(chybatext)-len(chybaveta)) # ak
                        chybaveta= chybaveta+(dlzkavety*"*")
                    for i in range (len(chybatext)): # určujem chybu ale zatial iba ked su rovnako velké
                        if chybatext[i]!=chybaveta[i]:
                            Chyby+=1
                        elif chybatext[i]== chybaveta[i] :
                            pocetpresnost+=1
                    if Chyby>maxskore:
                        Chyby=maxskore
                    
                    
                    
    
                    ##################### premene pre čas chybu atď....        
                    skore[0]= str(maxskore-Chyby)
                    presnost[0]=round((pocetpresnost/lenveta)*100)
                    presnost[0]=str(presnost[0])+"%"
                    chyby[0]=str(Chyby)
                    end=time.perf_counter() # tu končíme počitanie času
                    cas1=round(end-start,2)
                    cas[0]=round(end-start,2)
                    cas[0]=str(cas[0])+" sek"
                    if (pocetStlacKlaves-2)<=0: # toto budes musiet potom zmeniť iba na -1 lebo keď tam daš button a potom všade
                        pocetznakov[0]="0 u/s"
                    else:
                        pocetznakov[0]=round(((pocetStlacKlaves-2)/cas1),2)  # toto je vypočet počet uderov za čas
                        pocetznakov[0]= str(pocetznakov[0])+" u/s" # takto pridaš jednotku k hodnote
                    txtskore=open("TXT/tempskore.txt","r")
                    skorelvl=txtskore.readline()
                    skore1=int(skorelvl)+int(skore[0])
                    txtskore.close()
                    txtskore=open("TXT/tempskore.txt","w")
                    txtskore.write(str(skore1))
                    txtskore.close()
                    #########
                    txtcas=open("TXT/tempcas.txt","r")
                    caslvl=txtcas.readline()
                    cas1=float(caslvl)+round(end-start,2)
                    txtskore.close()
                    txtcas=open("TXT/tempcas.txt","w")
                    txtcas.write(str(cas1))
                    txtcas.close()
                
                else: # pisanie 
                    userText += event.unicode # pišeš pridavaš do stringu
            
                        
        if ukazstats==1:
            textStats=fontNadpis.render("STATS:",True,(vysfarba))
            win.blit(textStats,(0,0))
            
            textskore = fontNadpis.render("SKÓRE: "+skore[0],True,(255,0,0)) # vyoisujeme skóre
            win.blit(textskore,(460,200))
        
            textpresnost = fontStats.render("presnosť: "+presnost[0],True,vysfarba) # vyoisujeme presnosť 
            win.blit(textpresnost,(0,150))
        
            textrychlost = fontStats.render("rýchlosť: "+pocetznakov[0],True,vysfarba) # vyoisujeme chybu 
            win.blit(textrychlost,(0,200))
        
            textchyby = fontStats.render("počet chýb: "+chyby[0],True,vysfarba) # vyoisujeme chybu 
            win.blit(textchyby,(0,100))
        
            textcas = fontStats.render("čas: "+cas[0],True,vysfarba) # vypisujeme čas
            win.blit(textcas,(0,50))
            
            x,y,w,h = (940,485,150,50) # button na next level vykreslenie
            
            NextlevelBut=pygame.image.load("IMG/nextLevel.jpg")
            NextlevelBut=pygame.transform.scale(NextlevelBut,(150,50))
            win.blit(NextlevelBut,(940,485))
            #pygame.draw.rect(win,(255,255,255),nextlevelbutton)
            #textlevel = font.render("čas:",True,vysfarba) # vypisujeme čas # toto uz tiež nepotrebuješ
            #win.blit(nextlevelbutton,(1000,100))
            
            
            x1,y1,w1,h1=(160,485,150,50)
            restartBut=pygame.image.load("IMG/RestartButton.png")
            restartBut=pygame.transform.scale(restartBut,(150,50))
            win.blit(restartBut,(160,485))
            
        
        #########################
        text = font.render(userText,True,(0,0,0)) # vypisujeme pisany text   totoo musí byť na konci inak sa to tomu nieco stale nepačí
        win.blit(text,(5,440))

        
        
        
        pygame.display.update() #  zase updatujeme lebo osobitny cyklus pre danu hru
        clock.tick(60) # "FPS"

def vetylvl3(): # takúto funkciu pre každy lvl ak bude aj obtiažnost tak 2..n fuknci pre dany level zaleží kolko obtiažnosti bude
    cislo=""
    file=open("TXT/obtiaznost.txt","r")
    obtiaznost=file.readline()
    if obtiaznost=="Easy":
        f=open("LEVEL/LVL3.txt","r")
    else:
        f=open("LEVEL/H_LVL3.txt","r")
    cislo=random.randint(0,2)
    if cislo==0:
        return f.readline()
    elif cislo==1:
        f.readline() 
        return f.readline()
    elif cislo==2:
        f.readline()
        f.readline()    
        return f.readline()
########################################################################################## LEVEL  3 ############################################################################################ 

########################################################################################## LEVEL  4   ############################################################################################ 
def lvl4():
    win=pygame.display.set_mode((1100,550))
    run=True # ak je true pobeží ked tak kukni to video pre vysveltenie
    fontNadpis=pygame.font.SysFont("Arial Black",32)
    fontStats=pygame.font.SysFont("calibri",40)
    font=pygame.font.SysFont("Calibri Light",32)
    vysfarba=(255,255,255)
    veta=""
    veta1=vetylvl4() # toto budes meniť v každom levely #################################################################################################################################################
    for i in range(len(veta1)-1): # toto je kvoli tomu že na konci sa robi nejaky neznamy znak a neviem aky tak takto inak neviem replace nefunguje lebo nepoznam znak ani join mi nejak nejde
        veta+=veta1[i]
    lenveta=len(veta1)-1
    maxskore=10 # aj toto budeš meniť pri každom levely #################################################################################################################################################
    
    end=0
    start=0
    userText=""
    cas1=0 # pomocna
    pocetStlacKlaves=0 # pomocna pre stalečenie klaves
    Chyby=0 # premenna na chyby 
    dlzkavety=0 # pomocna pre vety
    pocetpresnost=0
    # namiesto tohto daš button asi naskor....
    stlacStart=0 # zamedzujeme crashu
    cas=[""] # kdyby neco davam to listu lebo to potrebujem uložit z iteracie!!!! nezabudni
    pocetznakov=[""]
    chyby=[""] # toto je ten isty prípad
    chybatext=""
    chybaveta=""
    skore=[""]# skóre je počet znakov vo vete minus pocet chyb
    presnost=[""]
    Starkey="PRESS STARTBUTTON TO START!"
    ukazstats=0
    x,y,w,h=0,0,0,0
    x1,y1,w1,h1=0,0,0,0
    while run:
        nextlevelbutton=pygame.Rect(x,y,w,h)
        pygame.draw.rect(win,(255,255,255),nextlevelbutton)
        restartButton = pygame.Rect(x1,y1,w1,h1)
        pygame.draw.rect(win,(0,0,0),restartButton)
        click=False
        win.fill((0,150,255))
        background=pygame.image.load("IMG/game_BG.jpg")
        background=pygame.transform.scale(background,(1100,700))
        win.blit(background,(0,0))
        chybatext=userText
        chybaveta=veta
        # mozem menit aj farbu 
        #text= font.render("game.... WIP", True,(255, 255, 255)) # menu nazov a farba to iste vykreslujeme nazov ### toto bolo iba pre ukažku
        #win.blit(text, (1,1)) # vykreslovanie
        startkey=font.render(Starkey,True,(255,255,255)) # podstatne vypisujeme vzorovú vetu ktorú nam hore 
        win.blit(startkey,(350,300))
        textlevel=fontNadpis.render("LEVEL 4",True,(255,255,255)) ########################################################################################################################
        win.blit(textlevel,(940,0))
        if stlacStart==1:
            Starkey=""
            vetaNadpis=fontNadpis.render("VETA:",True,(255,255,0))
            win.blit(vetaNadpis,(0,250))
            vetatext = pygame.Rect(5,300,1090,50)
            pygame.draw.rect(win,(80,80,80),vetatext)
            vetavzor=font.render(veta,True,(255,255,255)) # podstatne vypisujeme vzorovú vetu ktorú nam hore 
            win.blit(vetavzor,(5,315))
        elif stlacStart==0:
            Starkey="PRESS START BUTTON TO START!"
            win.blit(startkey,(350,300))
        
        MysX,MysY=pygame.mouse.get_pos()
        
        wintext = pygame.Rect(5,425,1090,50)
        pygame.draw.rect(win,(255,255,255),wintext)
        
        startButton = pygame.Rect(5,485,150,50)
        pygame.draw.rect(win,(0,0,0),startButton)
        StartBut=pygame.image.load("IMG/StartButton.png")
        StartBut=pygame.transform.scale(StartBut,(150,50))
        win.blit(StartBut,(5,485))
        """
        restartButton = pygame.Rect(160,485,150,50)
        pygame.draw.rect(win,(0,0,0),restartButton)  ###########toto uz nebudeš potrebovať 
        restartBut=pygame.image.load("RestartButton.png")
        restartBut=pygame.transform.scale(restartBut,(150,50))
        win.blit(restartBut,(160,485))
        """
        for event in pygame.event.get():
            if event.type == pygame.QUIT: # vypnutie cez X
                pygame.quit()
                sys.exit()
                stlacStart=0
            if event.type ==pygame.MOUSEBUTTONDOWN:
                if event.button == 1:
                    click= True
            if startButton.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    userText=""
                    stlacStart=1
                    # ošetruejeme aby sa všetko vynoloval pokial niesu zapnute stopky
                    start=time.perf_counter() # začiname pocitat čas
            if nextlevelbutton.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    vysSkore()
                    #lvl4()
                    #pygame.quit() # toto musis potom zmeniť ked to budeš davat to menu skriptu lebo teraz to mas aby ti to fungovalo mimo skriput
                    #sys.exit() #################################################################################################################################################
            if restartButton.collidepoint(MysX,MysY): # ak dojde ku kliku mysov
                if click:
                    userText=""
                    # ošetruejeme aby sa všetko vynoloval pokial niesu zapnute stopky
                    start=time.perf_counter() # začiname pocitat čas
                    stlacStart=0
                    chyby[0]=""
                    pocetznakov[0]=""      # toto použižeš aj na rešart levelu
                    cas[0]=""
                    skore[0]=""
                    pocetStlacKlaves=0
                    pocetpresnost=0
                    presnost[0]=""
                    ukazstats=0
            
                    
            if event.type == pygame.KEYDOWN:
                pocetStlacKlaves+=1 #pocet stlačených klaves
                if event.key== pygame.K_ESCAPE: # vypnutie cez ESC
                     run=False # toto musis potom zmeniť ked to budeš davat to menu skriptu lebo teraz to mas aby ti to fungovalo mimo skriput
                     menu()      # aj ten run musis dať aby ti to pootom išlo
                elif event.key == pygame.K_BACKSPACE:
                    userText = userText[0:-1]
                elif event.key == pygame.K_RETURN and stlacStart==1:
                    ukazstats=1
                    if (len(chybatext))<len(chybaveta):
                      dlzkavety=(len(chybaveta)-len(chybatext)) # chybou text to nahradzuješ aby neprepisalo vetu ktoru pišeš
                      chybatext= chybatext+(dlzkavety*"*")
                    elif (len(chybatext))>len(chybaveta):
                        dlzkavety=(len(chybatext)-len(chybaveta)) # ak
                        chybaveta= chybaveta+(dlzkavety*"*")
                    for i in range (len(chybatext)): # určujem chybu ale zatial iba ked su rovnako velké
                        if chybatext[i]!=chybaveta[i]:
                            Chyby+=1
                        elif chybatext[i]== chybaveta[i] :
                            pocetpresnost+=1
                    if Chyby>maxskore:
                        Chyby=maxskore
                    
                    
                    
    
                    ##################### premene pre čas chybu atď....        
                    skore[0]= str(maxskore-Chyby)
                    presnost[0]=round((pocetpresnost/lenveta)*100)
                    presnost[0]=str(presnost[0])+"%"
                    chyby[0]=str(Chyby)
                    end=time.perf_counter() # tu končíme počitanie času
                    cas1=round(end-start,2)
                    cas[0]=round(end-start,2)
                    cas[0]=str(cas[0])+" sek"
                    if (pocetStlacKlaves-2)<=0: # toto budes musiet potom zmeniť iba na -1 lebo keď tam daš button a potom všade
                        pocetznakov[0]="0 u/s"
                    else:
                        pocetznakov[0]=round(((pocetStlacKlaves-2)/cas1),2)  # toto je vypočet počet uderov za čas
                        pocetznakov[0]= str(pocetznakov[0])+" u/s" # takto pridaš jednotku k hodnote
                    txtskore=open("TXT/tempskore.txt","r")
                    skorelvl=txtskore.readline()
                    skore1=int(skorelvl)+int(skore[0])
                    txtskore.close()
                    txtskore=open("TXT/tempskore.txt","w")
                    txtskore.write(str(skore1))
                    txtskore.close()
                    ########
                    txtcas=open("TXT/tempcas.txt","r")
                    caslvl=txtcas.readline()
                    cas1=float(caslvl)+round(end-start,2)
                    txtskore.close()
                    txtcas=open("TXT/tempcas.txt","w")
                    txtcas.write(str(cas1))
                    txtcas.close()
                
                else: # pisanie 
                    userText += event.unicode # pišeš pridavaš do stringu
            
                        
        if ukazstats==1:
            textStats=fontNadpis.render("STATS:",True,(vysfarba))
            win.blit(textStats,(0,0))
            
            textskore = fontNadpis.render("SKÓRE: "+skore[0],True,(255,0,0)) # vyoisujeme skóre 
            win.blit(textskore,(460,200))
        
            textpresnost = fontStats.render("presnosť: "+presnost[0],True,vysfarba) # vyoisujeme presnosť 
            win.blit(textpresnost,(0,150))
        
            textrychlost = fontStats.render("rýchlosť: "+pocetznakov[0],True,vysfarba) # vyoisujeme chybu 
            win.blit(textrychlost,(0,200))
        
            textchyby = fontStats.render("počet chýb: "+chyby[0],True,vysfarba) # vyoisujeme chybu 
            win.blit(textchyby,(0,100))
        
            textcas = fontStats.render("čas: "+cas[0],True,vysfarba) # vypisujeme čas
            win.blit(textcas,(0,50))
            
            x,y,w,h = (940,485,150,50) # button na next level vykreslenie
            
            NextlevelBut=pygame.image.load("IMG/finish.png")
            NextlevelBut=pygame.transform.scale(NextlevelBut,(150,50))
            win.blit(NextlevelBut,(940,485))
            #pygame.draw.rect(win,(255,255,255),nextlevelbutton)
            #textlevel = font.render("čas:",True,vysfarba) # vypisujeme čas # toto uz tiež nepotrebuješ
            #win.blit(nextlevelbutton,(1000,100))
            
            
            x1,y1,w1,h1=(160,485,150,50)
            restartBut=pygame.image.load("IMG/RestartButton.png")
            restartBut=pygame.transform.scale(restartBut,(150,50))
            win.blit(restartBut,(160,485))
            
        
        #########################
        text = font.render(userText,True,(0,0,0)) # vypisujeme pisany text   totoo musí byť na konci inak sa to tomu nieco stale nepačí
        win.blit(text,(5,440))

        
        
        
        pygame.display.update() #  zase updatujeme lebo osobitny cyklus pre danu hru
        clock.tick(60) # "FPS"

def vetylvl4(): # takúto funkciu pre každy lvl ak bude aj obtiažnost tak 2..n fuknci pre dany level zaleží kolko obtiažnosti bude
    cislo=""
    file=open("TXT/obtiaznost.txt","r")
    obtiaznost=file.readline()
    if obtiaznost=="Easy":
       f=open("LEVEL/LVL4.txt","r")
    else:
       f=open("LEVEL/H_LVL4.txt","r")
    cislo=random.randint(0,2)
    if cislo==0:
        return f.readline()
    elif cislo==1:
        f.readline() 
        return f.readline()
    elif cislo==2:
        f.readline()
        f.readline()    
        return f.readline()

########################################################################################## LEVEL  4   ############################################################################################ 


########################### pomocna funckia ########################### pomocna funkcia ###########################  pomocna funckia###########################
def vysSkore():
    userText=""
    meno=""
    UkazPremena=False
    #win=pygame.display.set_mode((500,500))
    run=True # ak je true pobeží ked tak kukni to video pre vysveltenie
    #size=(500,500)
    win=pygame.display.set_mode((500,600))
    fontStats=pygame.font.SysFont("calibri",40)
    fontNadpis=pygame.font.SysFont("Arial Black",40)
    skorepomoc=[]
    while run:
        meno=[]
        win.fill((0,127,127)) # zmenin si farbu
        BG=pygame.image.load("IMG/KONIEC_BG.jpg")
        BG=pygame.transform.scale(BG,(500,600))
        win.blit(BG,(0,0))
        text= fontNadpis.render("Meno hrača:", True,(255, 255, 255)) # menu nazov a farba to iste vykreslujeme nazov
        win.blit(text, (5,0)) # vykreslovanie
        meno=pygame.Rect(5,70,300,50)
        pygame.draw.rect(win,(255,255,255),meno)
        
        for event in pygame.event.get():
            if event.type == pygame.QUIT: # vypnutie cez X
                pygame.quit()
                sys.exit()
            if event.type == pygame.KEYDOWN:
                if event.key== pygame.K_ESCAPE: # vypnutie cez ESC
                    run=False
                    menu()
                elif event.key == pygame.K_BACKSPACE:
                    userText = userText[0:-1]
                elif event.key == pygame.K_RETURN:
                    UkazPremena=True
                    
                    txtskore=open("TXT/tempskore.txt","r")
                    skorepomoc=txtskore.readline()
                    txtdotxt=[userText," ",str(skorepomoc)+"\n"]
                    txtzoznam=open("TXT/zoznam.txt","a")
                    txtzoznam.writelines(txtdotxt)
                    txtzoznam.close()
                    txtskore.close()
                    
                    
    
                    
                    
                    
                else: # pisanie 
                    userText += event.unicode # pišeš pridavaš do stringu
        if UkazPremena==True:
            txtskore=open("TXT/tempskore.txt","r")
            skorecelkove=txtskore.readline()
            txtcas=open("TXT/tempcas.txt","r")
            priemcas=txtcas.readline()
            priemcas=float(priemcas)
            txtskore.close()
            txtcas.close()
            
            #ZoradSkore(skorecelkove,priemcas,userText)# toto uz mam 
            zobrazSkore=fontNadpis.render("Celkové skóre :",True,(255,255,255))
            win.blit(zobrazSkore,(50,158))
            zobrazCas=fontNadpis.render("Celkový caš :",True,(255,255,255))
            win.blit(zobrazCas,(65,358))
           
            pozadieskore = pygame.Rect(190,260,80,55)
            pygame.draw.rect(win,(255,255,255),pozadieskore)
            pozadiecas = pygame.Rect(155,460,165,55)
            pygame.draw.rect(win,(255,255,255),pozadiecas)
           
            
            CelkoveSkore= fontNadpis.render(skorecelkove,True,(0,0,0)) # vypisujeme pisany text   totoo musí byť na konci inak sa to tomu nieco stale nepačí
            win.blit(CelkoveSkore,(200,258))
            Priemcas=fontNadpis.render(str(round(priemcas,2)),True,(0,0,0))
            win.blit(Priemcas,(175,458))
            
        ##################################################TOTO SPODOK######################################
        text = fontStats.render(userText,True,(0,0,0)) # vypisujeme pisany text   totoo musí byť na konci inak sa to tomu nieco stale nepačí
        win.blit(text,(5,78))
        ###########################
        pygame.display.update() #  zase updatujeme lebo osobitny cyklus pre danu hru
        clock.tick(60) # "FPS"
    
  
    ########################### pomocna funckia ########################### pomocna funkcia ###########################  pomocna funckia###########################
  
    
  
    
  
    
  

menu() # volame funckiu aby sa sputila a spustila hru
"""
import pygame,sys
import timeit
pygame.init()
clock = pygame.time.Clock()
okno = pygame.display.set_mode([800,800])
textFont = pygame.font.Font(None,32)
userText = ""
okno.fill((255,255,255))
state=0
start=0
end=0
def nieco():
while True:
    
    for event in pygame.event.get():
        if event.type == pygame.QUIT:
            pygame.quit()
            sys.exit()
        
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_BACKSPACE:
                userText = userText[0:-1]
            #elif event.key == pygame.K_SPACE:
                #start = timeit.default_timer()
            #elif event.key == pygame.K_RETURN:
                #end = timeit.default_timer()
                #print(round(end - start,2),"Sekund")
            else:
                userText += event.unicode
        if event.type == pygame.KEYDOWN:
            if event.key == pygame.K_BACKSPACE:
                userText = userText[0:-1]
            else:
                userText += event.unicode      
    
    
    okno.fill((255,255,255))
    text = textFont.render(userText,True,(0,0,0)) 
    okno.blit(text,(10,10))

    pygame.display.flip()
    clock.tick(60)
nieco()
"""         
        
        