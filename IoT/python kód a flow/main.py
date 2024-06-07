from machine import Pin,ADC
import utime as t
import dht
from umqtt.simple import MQTTClient
import ujson
from hcsr04 import HCSR04


### inicializacia premmených ###

# MQTT udaje //vymazane z bezpecnostných dovodou
server = "xxxx.xxxx.xxxx.xxxx"
port = 2188
username = "xxxxx"
password = "xxxxx"

# Pins
ledVentilation = Pin(5,Pin.OUT)
ledShading = Pin(15,Pin.OUT)
ledWatering = Pin(0,Pin.OUT)  # invertovana logika on = off a naopak zapnem inbuilt ledku
ledOnOffDevice = Pin(2,Pin.OUT)
tempHumidInput = dht.DHT11(Pin(14))
lightLevel = ADC(0)
distanceSensor = HCSR04(trigger_pin=13, echo_pin=12, echo_timeout_us=10000)
button = Pin(4,Pin.IN)

#ledShading.off()
#ledVentilation.off()

# MQTT incializacia objektu a connect
mqttClient = MQTTClient("s10",server,port=port,user=username,password=password,keepalive=3000)
mqttClient.connect()

controlFromWeb = False
mqttClient.publish("s10/manualControlToNodeRed","on")

# callback metoda tu bude logika prijatých sprav
def sub_cb(topic,msg):
    
    #Automatic ventilation
    if topic.decode() == "s10/humid&tempControl" and msg.decode() == "on":
        ledVentilation.on()
    elif topic.decode() == "s10/humid&tempControl" and msg.decode() == "off":
        ledVentilation.off()
        
    #Automatic shading    
    if topic.decode() == "s10/shading" and msg.decode() == "on":
        ledShading.on()
    elif topic.decode() == "s10/shading" and msg.decode() == "off":
        ledShading.off()
    
    #Automatic watering
    if topic.decode() == "s10/watering" and msg.decode() == "on":
        ledWatering.on()
    if topic.decode() == "s10/watering" and msg.decode() == "off":
        ledWatering.off()
           
        
# nastavnie callbacku
mqttClient.set_callback(sub_cb)

# subcribe na temy
mqttClient.subscribe("s10/humid&tempControl")
mqttClient.subscribe("s10/shading")
mqttClient.subscribe("s10/watering")
#mqttClient.subscribe("s10/manualControlFromNodeRed")


controlFromButton = False
stoper = 0
buttonPressed = False
#main loop
while True:
    # prichadzajuce spravy
    mqttClient.check_msg()
    
    
    if stoper == 10:
        
        buttonValue = button.value()
        if buttonValue == 1 and buttonPressed == False:
            controlFromButton = True
            buttonPressed = True
            mqttClient.publish("s10/manualControlToNodeRed","off")
        # publishne
        elif buttonValue == 1 and buttonPressed == True:
            controlFromButton = False
            buttonPressed = False
            mqttClient.publish("s10/manualControlToNodeRed","on")
        
        if not controlFromButton:
            ledOnOffDevice.off()
            tempHumidInput.measure()
            #kazdu sekundu bude meriat

            #temp = tempHumidInput.temperature();
            distance = distanceSensor.distance_mm()
        
            #Jsonujem udaje a posielam na publishujem
            jsonDataOut = ujson.dumps({
                "temp":tempHumidInput.temperature(),
                "humid": tempHumidInput.humidity(),
                "lightLevel": lightLevel.read()/1024*1000,
                "waterLevel": distance, # 40 mm musim dať  
                })
            mqttClient.publish("s10/temp&humid&light&distance",jsonDataOut)#     
    #         # toto inak sprav alebo vyma
        elif controlFromButton:
            ledOnOffDevice.on()
        
        stoper = 0
    stoper += 1
            
    t.sleep(0.1)
    
    


        




