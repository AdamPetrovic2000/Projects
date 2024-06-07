% zadam bitovú postupnosť a vrati mi časový vektor a hodnoty v čase 
% vybraneého typu linkoveho kódu a tie pojdu do GUI a tam budem riešit
%vykreslenie oznacenie osi a pod.
function [casovyVektor,hodnoty,titleString,error,dlzka] = linkoveKody(type,bitovePole)

% tu pojde ešte parsovanie aby to bolo bitové pole
% konverzia stringu na pole cisel
bitovePole = char(bitovePole);
output = zeros(1,length(bitovePole));
error = false;
for i = 1:length(bitovePole)
    cislo = str2num(bitovePole(i));
    if cislo > 1
        error = true;
    elseif cislo < 0
        error = true;
    else
        output(i) = cislo;
    end
end
bitovePole = output;
dlzka = length(bitovePole);

switch type
    
    case "UP_NRZ"
        titleString = "Unipolárny linkový kódy bez návratu k nule";
        %% UP_NRZ
        n = length(bitovePole);
        x = [];
        y = [];

        for i = 1:n
            x = [x i-1 i];
            if(bitovePole(i)==0)
                y = [y 0 0];
            else
                y = [y 1 1];
            end
        end
        casovyVektor = x;
        hodnoty = y;
    case "BP_NRZ"
        %% BP_NRZ
        titleString = "Bipolárny linkový kódy bez návratu k nule";
        n = length(bitovePole);
        x = [];
        y = [];
        for i=1:n
            x=[x i-1 i];
            if(bitovePole(i)==0)
                y=[y -1 -1];
            else
                y=[y 1 1];
            end
        end
        casovyVektor = x;
        hodnoty = y;
    case "UP_RZ"
        %% UP_RZ
        titleString = "Unipolárny linkový kódy s návratom k nule";
        n = length(bitovePole);
        x = [];
        y = [];

        for i=1:n
            x=[x i-1 i-1+0.5 i-1+0.5 i];
            if(bitovePole(i)==0)
                y=[y 0 0 0 0];
            else
                y=[y 1 1 0 0];
            end
        end
        casovyVektor = x;
        hodnoty = y;
    case "BP_RZ"
        %% BP_RZ
        titleString = "Bipolárny linkový kódy s návratom k nule";
        n = length(bitovePole);
        x = [];
        y = [];

        for i=1:n
            x=[x i-1 i-1+0.5 i-1+0.5 i];
            if(bitovePole(i)==0)
                y=[y -1 -1 0 0];
            else
                y=[y 1 1 0 0];
            end
        end
        casovyVektor = x;
        hodnoty = y;
    case "AMI_NRZ"
        %% AMI_NRZ
        titleString = "Alternate Mark Inversion linkový kódy bez návratu k nule";
        n=1;
        l=length(bitovePole);
        bitovePole(l+1)=1;
        ami=-1;

        hodnotyLocal = zeros(1,l*1000);
        casovyVektorLocal = zeros(1,l*1000);

        while n<=length(bitovePole)-1
            x=n-1:0.001:n;
            poshodnota1 = ((n-1)*1000)+1; % potrebujem pocitat s nulou
            if bitovePole(n) == 0
                if bitovePole(n+1)==0
                    y=(x>n);
                else
                    if ami==1
                        y=-(x==n);
                    else
                        y=(x==n);
                    end
                end
                hodnotyLocal(poshodnota1:poshodnota1+1000) = y;
                casovyVektorLocal(poshodnota1:poshodnota1+1000) = x;
            else
                ami=ami*-1;
                if bitovePole(n+1)==0
                    if ami==1
                        y=(x<n);
                    else
                        y=-(x<n);
                    end
                else
                    if ami==1
                        y=(x<n)-(x==n);
                    else
                        y=-(x<n)+(x==n);
                    end
                end
                hodnotyLocal(poshodnota1:poshodnota1+1000) = y;
                casovyVektorLocal(poshodnota1:poshodnota1+1000) = x;
                
            end
            n=n+1;
        end
        casovyVektor = casovyVektorLocal;
        hodnoty = hodnotyLocal;
    case "AMI_RZ"
        %% AMI_RZ
        titleString = "Alternate Mark Inversion linkový kódy s návratom k nule";
        n=1;
        l=length(bitovePole);
        bitovePole(l+1)=1;
        ami=-1;
        hodnotyLocal = zeros(1,l*1000);
        casovyVektorLocal = zeros(1,l*1000);


        while n<=length(bitovePole)-1
            x=n-1:0.001:n;
            poshodnota1 = ((n-1)*1000)+1; % potrebujem pocitat s nulou
            if bitovePole(n) == 0
                if bitovePole(n+1)==0
                    y=(x>n);
                else
                    if ami==1
                        y=-(x==n);
                    else
                        y=(x==n);
                    end
                end
                hodnotyLocal(poshodnota1:poshodnota1+1000) = y;
                casovyVektorLocal(poshodnota1:poshodnota1+1000) = x;
            else
                ami=ami*-1;
                if bitovePole(n+1)==0
                    if ami==1
                        y=(x<n-0.5);
                    else
                        y=-(x<n-0.5);
                    end
                else
                    if ami==1
                        y=(x<n-0.5)-(x==n);
                    else
                        y=-(x<n-0.5)+(x==n);
                    end

                end
                hodnotyLocal(poshodnota1:poshodnota1+1000) = y;
                casovyVektorLocal(poshodnota1:poshodnota1+1000) = x;

            end
            n=n+1;
        end
        casovyVektor = casovyVektorLocal;
        hodnoty = hodnotyLocal;
    case "Manchester"
        %% Manchester
        titleString = "Bi–Φ–L linkový kód - Manchester kód";
        n=1;
        bitovePole=~bitovePole;
        l=length(bitovePole);
        bitovePole(l+1)=1;

        hodnotyLocal = zeros(1,l*1000);
        casovyVektorLocal = zeros(1,l*1000);

        while n<=length(bitovePole)-1
            x=n-1:0.001:n;
            poshodnota1 = ((n-1)*1000)+1; % potrebujem pocitat s nulou
            if bitovePole(n) == 0
                if bitovePole(n+1)==0
                    y=-(x<n)+2*(x<n-0.5)+1*(x==n);
                else
                    y=-(x<n)+2*(x<n-0.5)-1*(x==n);
                end
                hodnotyLocal(poshodnota1:poshodnota1+1000) = y;
                casovyVektorLocal(poshodnota1:poshodnota1+1000) = x;
            else
                if bitovePole(n+1)==0
                    y=(x<n)-2*(x<n-0.5)+1*(x==n);
                else
                    y=(x<n)-2*(x<n-0.5)-1*(x==n);
                end

                hodnotyLocal(poshodnota1:poshodnota1+1000) = y;
                casovyVektorLocal(poshodnota1:poshodnota1+1000) = x;
            end
            n=n+1;
        end
        casovyVektor = casovyVektorLocal;
        hodnoty = hodnotyLocal;     
end

