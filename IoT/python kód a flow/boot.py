# boot.py -- run on boot-up
# iotlab
# plus je dlha nožička
import network

def connect():
    sta_if = network.WLAN(network.STA_IF)
    if not sta_if.isconnected():
        sta_if.active(True)
        
        #V skole#################################
        sta_if.connect('iotlab', 'XXXXX') # tu davas meno a heslo Wifi
        
        # doma##################################
        if not sta_if.isconnected():
            sta_if.connect('Xiaomi', 'XXXXX')
            
        
        while not sta_if.isconnected():
        # wait till connection
            pass
    
    # vypise do konzoly SSID Wifi
    print("connected to:", sta_if.config('essid'))
    print('network config:', sta_if.ifconfig())
   
    
#connection
connect()
