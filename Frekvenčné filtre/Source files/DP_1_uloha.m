f = 0:100000; % vektor frekvencie
w = 2*pi*f; % uhlova frekvencia
R = 100; % odpor rezistora v Ω
C = 10e-6;% kapacita kapicitora v F
Hwj = 1./(1+1j*w*R*C); % frekvenčna odozva systemu
HwjdB = mag2db(Hwj); % v logaritmickej mierke
fiRadian = -atan(w*R*C); % vztah pre fazovú charakteristiku
fiStupen = rad2deg(fiRadian); % prevod z radianov na stupne
subplot(2,1,1)
vykreslsen = semilogx(f,HwjdB);% vykreslenie frekvečnej charakteristiky
ylabel("A [dB]") % oznacenie y osi
xlabel("f [Hz]") % oznacenie x osi
vykreslsen.LineWidth = 2;
hold on 
ylim([-60,1])
fc = 1/(2*pi*R*C) % vypočet mezdnej frekvencie
hold on
plot([1 f(end)],[-3.01 -3.01],"Color","k"); % vykresli priamku na x osi
plot([fc fc],[-60 1],"Color","k"); % vykresli priamku na y osi
text(fc,-62,"fc="+round(fc)+" Hz","Color","b","FontWeight","bold")
text(0.5,-4.51,"-3.01 dB","Color","b","FontWeight","bold")
title("Amplitúdovo-frekvenčná charakteristika")
subplot(2,1,2)
vykresly2 = semilogx(f,fiStupen); % vykreslenie fazovej charakteristiky
vykresly2.LineWidth = 2;
ylabel("φ [°]") % oznacenie y osi
xlabel("f [Hz]") % oznacenie x osi
hold on
fic = rad2deg(-atan(2*pi*fc*R*C)); % vypočet zmeny fázy na mezdnej frekvencii
plot([1 f(end)],[-45 -45],"Color","k");
plot([fc fc],[-100 0],"Color","k");
text(fc,-104,"fc="+round(fc)+" Hz","Color","b","FontWeight","bold")
text(0.7,-46,"-45°","Color","b","FontWeight","bold")
title("Fázovo-frekvenčná charakteristika")

