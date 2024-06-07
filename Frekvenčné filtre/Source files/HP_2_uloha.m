f = 0:100000;
w = 2*pi*f;
R = 2000;
L = 100e-3;
fc = R/(2*pi*L)

Hwj = 1./(1+(R./(1j*w*L)));
HwjdB = mag2db(Hwj);
subplot(2,1,1)
semilogx(f,HwjdB);
ylabel("H(jω) [dB]") % označenie y osi
xlabel("f [Hz]") % označenie x osi
phase = atan((R./(w*L)));
subplot(2,1,2)
semilogx(f,rad2deg(phase))
ylabel("φ [°]") % označenie y osi
xlabel("f [Hz]") % označenie x osi