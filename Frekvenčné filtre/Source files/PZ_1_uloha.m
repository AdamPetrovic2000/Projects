f = 0:1000000; % vektor frekvencie
w = 2*pi*f; % uhlová frekvencia
R = 200; % odpor rezistora v Ω
C = 20e-6;% kapacita kapacitora v F
L = 1e-3;% kapacita induktora v H
fc1 = -(R/(4*pi*L))+(1/(2*pi))*sqrt(((R/(2*L))^2+(1/(L*C)))) %fc1
fc2 = (R/(4*pi*L))+(1/(2*pi))*sqrt(((R/(2*L))^2+(1/(L*C))))%fc2
fi1 = rad2deg(-atan(((fc1*2*pi)./(R*C))./((1/(L*C))-((2*pi*fc1).^2))));% výpočet fázy na pre fc1
fi2 = rad2deg(-atan(((fc2*2*pi)./(R*C))./((1/(L*C))-((2*pi*fc2).^2))));% výpočet fázy na pre fc2
Hwj = 1./(1-(1j*R)*(1./(w*L-(1./(w*C)))));% frekvenčná odozva 
HwjdB = mag2db(Hwj); % v logaritmickej mierke
fiRadian = -atan((w./(R*C))./((1/(L*C))-(w.^2))); % vzťah pre fázovú charakteristiku
fiStupen = rad2deg(fiRadian); % prevod z radiánov na stupne
subplot(2,1,1)
semilogx(f,HwjdB)% vykreslenie frekvenčnej charakteristiky
ylabel("H(jω) [dB]") % označenie y osi
xlabel("f [Hz]") % označenie x osi
text(fc1,-3,01,"O","Color","r") % x-os medzná frekvencia fc1, y-os pokles o 3dB
text(fc2,-3,01,"O","Color","r") % x-os medzná frekvencia fc2, y-os pokles o 3dB
subplot(2,1,2)
semilogx(f,fiStupen) % vykreslenie fázovej charakteristiky
ylabel("φ [°]") % označenie y osi
xlabel("f [Hz]") % označenie x osi
text(fc1,fi1,"o","Color","r") % x-os medzná fc1, y-os uhol fi1
text(fc2,fi2,"o","Color","r") % x-os medzná fc2, y-os uhol fi2
