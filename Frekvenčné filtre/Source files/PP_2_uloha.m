f = 0:1000000; % vektor frekvencie
w = 2*pi*f; % uhlová frekvencia
R = 25; % odpor rezistora v Ω
C = 3e-6;% kapacita kapacitora v F
L = 8e-3;% kapacita induktora v H
fc1 = -(1/(4*pi*R*C))+(1/(2*pi))*sqrt(((1/(2*R*C))^2+(1/(L*C)))); %fc1
fc2 = (1/(4*pi*R*C))+(1/(2*pi))*sqrt(((1/(2*R*C))^2+(1/(L*C)))); %fc2

Hwj = 1./(1+(1j*R)*((w*C)-(1./(w*L))));% frekvenčná odozva
HwjdB = mag2db(Hwj);% frekvenčná odozva v dB
semilogx(f,HwjdB)% vykreslenie frekvenčnej charakteristiky
text(fc1-30,-3,01,"O","Color","r") % x-os medzná frekvencia fc1, y-os pokles o 3dB
text(fc2-50,-3,01,"O","Color","r") % x-os medzná frekvencia fc2, y-os pokles o 3dB
ylabel("H(jω) [dB]") % označenie y osi
xlabel("f [Hz]") % označenie x osi