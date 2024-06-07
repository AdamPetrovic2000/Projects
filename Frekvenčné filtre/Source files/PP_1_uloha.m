f = 0:1000000; % vektor frekvencie
w = 2*pi*f; % uhlová frekvencia
R = 200; % odpor rezistora v Ω
C = 20e-6;% kapacita kapacitora v F
L = 2e-3;% kapacita induktora v H
fc1 = -(R/(4*pi*L))+(1/(2*pi))*sqrt(((R/(2*L))^2+(1/(L*C)))) %fc1
fc2 = (R/(4*pi*L))+(1/(2*pi))*sqrt(((R/(2*L))^2+(1/(L*C))))%fc2
fi1 = rad2deg(atan((1/(L*C)-((fc1*2*pi)^2))/((fc1*2*pi)*(R/L))));% výpočet fázy na pre fc1
fi2 = rad2deg(atan((1/(L*C)-((fc2*2*pi)^2))/((fc2*2*pi)*(R/L))));% výpočet fázy na pre fc2
Hwj = 1./(1+(1j*(1/R))*((w*L)-(1./(w*C))));% frekvenčná odozva 
HwjdB = mag2db(Hwj); % v logaritmickej mierke
fiRadian = atan((1/(L*C)-(w.^2))./(w*(R/L))); % vzťah pre fázovú charakteristiku
fiStupen = rad2deg(fiRadian); % prevod z radiánov na stupne
subplot(2,1,1)
vyk = semilogx(f,HwjdB);% vykreslenie frekvenčnej charakteristiky
vyk.LineWidth = 2; 
ylabel("A [dB]") % označenie y osi
xlabel("f [Hz]") % označenie x osi
ylim([-30,1])
hold on
plot([1 f(end)],[-3.01 -3.01],"Color","k"); % vykresli priamku na x osi
plot([fc1 fc1],[-60 1],"Color","k"); % vykresli priamku na y osi
plot([fc2 fc2],[-60 1],"Color","k");
text(fc1,-31,"fc1","Color","b","FontWeight","bold")
text(fc2,-31,"fc2","Color","b","FontWeight","bold")
text(0.28,-3.01,"-3.01 dB","Color","b","FontWeight","bold")
subplot(2,1,2)
semilogx(f,fiStupen) % vykreslenie fázovej charakteristiky
ylabel("φ [°]") % označenie y osi
xlabel("f [Hz]") % označenie x osi
text(fc1,fi1,"o","Color","r") % x-os medzná fc1, y-os uhol fi1
text(fc2,fi2,"o","Color","r") % x-os medzná fc2, y-os uhol fi2





