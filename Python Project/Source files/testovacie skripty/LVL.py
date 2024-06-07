# -*- coding: utf-8 -*-
"""
Created on Mon Apr 25 19:40:28 2022

@author: Adam
"""

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
        
            textrychlost = fontStats.render("rychlosť: "+pocetznakov[0],True,vysfarba) # vyoisujeme chybu 
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
