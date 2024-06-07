f = 0:100000; % vektor frekvencie
w = 2*pi*f; % uhlova frekvencia
L = 100e-3; % hodnota induktora
R = 100; %hodnota odporu
fc = R/(2*pi*L); % vypočet mezdnej frekvencie
fic = rad2deg(atan(R/(2*pi*fc*L))); % vypočet zmeny fázy na mezdnej frekvencii 
Hwj = 1./(1+((1j*w*L)/R)); % frekvenčna odozva
HwjdB = mag2db(Hwj); % frekvenčna odozva v dB
fiRadian = -atan((w*L)/R); % fázova charakteristka
fiStupen = rad2deg(fiRadian); % prevod na stupne
subplot(2,1,1)
semilogx(f,HwjdB) % vykreslenie frekvenčnej charakteristky
%text(fc,-3,01,"O","Color","r") % x-os medzna frekvencia, y-os pokles o 3dB
ylabel("H(jω) [dB]") % označenie y osi
xlabel("f [Hz]") % označenie x osi
subplot(2,1,2)
semilogx(f,fiStupen) % vykreslenie fázovej charakteristiky
text(fc,fic,"o","Color","r") % x-os medzna frekvencia, y-os uloh 
ylabel("φ [°]") % označenie y osi
xlabel("f [Hz]") % označenie x osi
