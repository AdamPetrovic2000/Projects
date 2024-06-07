
f=open("SKORE.txt","r")
cas=0.32
meno="FERO"
skore=43
prve=f.readline()
druhe=f.readline()
tretie=f.readline()
prve=prve.split()
druhe=druhe.split()
tretie=tretie.split()
str1=""
str2=""
str3=""
if prve=="":
    f=open("SKORE.txt","w")
    writetext="NOBODY 0 0.0"+"\n"+"NOBODY 0 0.0"+"\n"+"NOBODY 0 0.0"
    f.write(writetext)
    f.close()
    ######### Ak je skore vascie ako prve
elif skore>int(prve[1]):
    tretie[0]=druhe[0]
    tretie[1]=druhe[1]
    tretie[2]=druhe[2]
    
    druhe[0]=prve[0]
    druhe[1]=prve[1]
    druhe[2]=prve[2]
   
       
    prve[0]=str(meno)
    prve[1]=str(skore)
    prve[2]=str(cas)
    str1=" ".join(prve)
    str2=" ".join(druhe)
    str3=" ".join(tretie)
    f=open("SKORE.txt","w")
    writetext=str1+"\n"+str2+"\n"+str3
    f.write(writetext)
    f.close()
###### ak je skore rovnake ale caš je menší
elif skore==int(prve[1]) and cas<float(prve[2]):
    tretie[0]=druhe[0]
    tretie[1]=druhe[1]
    tretie[2]=druhe[2]
    
    druhe[0]=prve[0]
    druhe[1]=prve[1]
    druhe[2]=prve[2]
   
       
    prve[0]=str(meno)
    prve[1]=str(skore)
    prve[2]=str(cas)
    str1=" ".join(prve)
    str2=" ".join(druhe)
    str3=" ".join(tretie)
    f=open("SKORE.txt","w")
    writetext=str1+"\n"+str2+"\n"+str3
    f.write(writetext)
    f.close()
    #### ak je skore rovnake ale caš je vacsi
elif skore==int(prve[1]) and cas>float(prve[2]):
    tretie[0]=druhe[0]
    tretie[1]=druhe[1]
    tretie[2]=druhe[2]
    
    druhe[0]=str(meno)
    druhe[1]=str(skore)
    druhe[2]=str(meno)
    
    prve[0]=prve[0]
    prve[1]=prve[1]
    prve[2]=prve[2]
   
    prve=prve
    str2=" ".join(druhe)
    str3=" ".join(tretie)
    str3=" ".join(tretie)
    f=open("SKORE.txt","w")
    writetext=str1+"\n"+str2+"\n"+str3
    f.write(writetext)
    f.close()
    #### ak je skore na druhom mieste vasčie ako aktualne
elif skore>int(druhe[1]):
    tretie[0]=druhe[0]
    tretie[1]=druhe[1]
    tretie[2]=druhe[2]
    
    druhe[0]=str(meno)
    druhe[1]=str(skore)
    druhe[2]=str(meno)
    
    prve[0]=prve[0]
    prve[1]=prve[1]
    prve[2]=prve[2]
   
    prve=prve
    str2=" ".join(druhe)
    str3=" ".join(tretie)
    str3=" ".join(tretie)
    f=open("SKORE.txt","w")
    writetext=str1+"\n"+str2+"\n"+str3
    f.write(writetext)
    f.close()
    # ak je skore rovnake ale čas je menši
elif skore==int(druhe[1]) and cas<float(druhe[2]):
    tretie[0]=druhe[0]
    tretie[1]=druhe[1]
    tretie[2]=druhe[2]
     
    druhe[0]=str(meno)
    druhe[1]=str(skore)
    druhe[2]=str(meno)
     
    prve[0]=prve[0]
    prve[1]=prve[1]
    prve[2]=prve[2]
    
    prve=prve
    str2=" ".join(druhe)
    str3=" ".join(tretie)
    str3=" ".join(tretie)
    f=open("SKORE.txt","w")
    writetext=str1+"\n"+str2+"\n"+str3
    f.write(writetext)
    f.close()
    # ak je skore rovnnake a Čas je vasci (2)
elif skore==int(druhe[1]) and cas>float(druhe[2]):
    tretie[0]=str(meno)
    tretie[1]=str(skore)
    tretie[2]=str(cas)
      
    druhe[0]=druhe[1]
    druhe[1]=druhe[1]
    druhe[2]=druhe[2]
      
    prve[0]=prve[0]
    prve[1]=prve[1]
    prve[2]=prve[2]
     
    prve=prve
    str2=" ".join(druhe)
    str3=" ".join(tretie)
    str3=" ".join(tretie)
    f=open("SKORE.txt","w")
    writetext=str1+"\n"+str2+"\n"+str3
    f.write(writetext)
    f.close()
elif skore>int(tretie[1]):
    tretie[0]=str(meno)
    tretie[1]=str(skore)
    tretie[2]=str(cas)
      
    druhe[0]=druhe[1]
    druhe[1]=druhe[1]
    druhe[2]=druhe[2]
      
    prve[0]=prve[0]
    prve[1]=prve[1]
    prve[2]=prve[2]
     
    prve=prve
    str2=" ".join(druhe)
    str3=" ".join(tretie)
    str3=" ".join(tretie)
    f=open("SKORE.txt","w")
    writetext=str1+"\n"+str2+"\n"+str3
    f.write(writetext)
    f.close()
elif skore==int(tretie[1]) and cas<float(tretie[2]):
    tretie[0]=str(meno)
    tretie[1]=str(skore)
    tretie[2]=str(cas)
      
    druhe[0]=druhe[1]
    druhe[1]=druhe[1]
    druhe[2]=druhe[2]
      
    prve[0]=prve[0]
    prve[1]=prve[1]
    prve[2]=prve[2]
     
    prve=prve
    str2=" ".join(druhe)
    str3=" ".join(tretie)
    str3=" ".join(tretie)
    f=open("SKORE.txt","w")
    writetext=str1+"\n"+str2+"\n"+str3
    f.write(writetext)
    f.close()
else:
    print("mimo skore")

