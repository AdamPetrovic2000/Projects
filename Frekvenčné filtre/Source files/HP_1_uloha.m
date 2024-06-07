f = 0:100000;
w = 2*pi*f;
R = 200;
C = 1e-6;
fc = 1/(2*pi*R*C)
Hwj = 1./(1+(1./(1j*w*R*C)));
HwjdB = mag2db(Hwj);
fi = atan((1./(w*R*C)));
fiStupen = rad2deg(fi);
subplot(2,1,1)
vyk = semilogx(f,HwjdB);
vyk.LineWidth = 2;
ylim([-60,1])
ylabel("A [dB]") % označenie y osi
xlabel("f [Hz]") % označenie x osi
hold on
plot([1 f(end)],[-3.01 -3.01],"Color","k"); % vykresli priamku na x osi
plot([fc fc],[-60 1],"Color","k"); % vykresli priamku na y osi
text(fc,-58,"fc="+round(fc)+"Hz","Color","b","FontWeight","bold")
text(0.38,-3.51,"-3.01 dB","Color","b","FontWeight","bold")
subplot(2,1,2)
semilogx(f,fiStupen)
ylabel("φ [°]") % označenie y osi
xlabel("f [Hz]") % označenie x osi



