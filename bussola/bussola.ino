#include <Wire.h>
#include "HMC5883L.h"

HMC5883L bussola;

void setup(){
	Serial.begin(9600);
	Wire.begin(); //Comunicacao I2C	

	//Configuração da bussola
  bussola = HMC5883L();
	bussola.SetScale(1.3);
	bussola.SetMeasurementMode(Measurement_Continuous);
}

void enviarGraus(int direcaoEmGraus){
    int tam = sizeof(direcaoEmGraus);
    char buffer[tam];
    memcpy( &buffer, &direcaoEmGraus, tam);
    Serial.write('I');
    //imprime a area de memoria referenciada por buffer
    Serial.write((uint8_t*)&buffer, tam);//escreve o buffer de tamanho tam no console
    Serial.write('F');
    Serial.write('\n');
}

int leituraEixos(){
    //Leitura dos Eixos "Brutos"
    MagnetometerRaw raw = bussola.ReadRawAxis(); 
    //Leitura dos Eixos em Escala
    MagnetometerScaled scaled = bussola.ReadScaledAxis(); 

    //int MilliGauss_EixoX = scaled.XAxis; sem necessidade
    float direcao = atan2(scaled.YAxis, scaled.XAxis); 
    
    float AnguloDeclinacao = ((-1)*23 + (20/60)) / (180/M_PI); //declinacao magnetica de VCA em rad
    if(AnguloDeclinacao<0)
        direcao -= AnguloDeclinacao;
    else
        direcao += AnguloDeclinacao;     

    //Correcao de angulacao
    if(direcao < 0){
        direcao += 2*PI;
    } 
      
    if(direcao > 2*PI){
        direcao -= 2*PI;
    }  
    
    //direcaoEmGraus = direcao * 180/M_PI; //Graus em Float
    int grausInt = direcao * 180/4;

    return grausInt;

}
void imprimir(int direcaoEmGraus){

    /* Relacao dos graus
    Grau | Ponto    | 
    0/360| Norte    | 
    90   | Leste    | 
    180  | Sul      | 
    270  | Oeste    | 
    45   | Nordeste | 
    135  | Sudeste  | 
    225  | Sudoeste |
    315  | Noroeste |
    */  
    Serial.print("Angulo: ");
    Serial.print(direcaoEmGraus);
    Serial.print("\t");
    Serial.print("Direcao: ");
    if (direcaoEmGraus <= 45 && direcaoEmGraus > 0){
            Serial.print("Nordeste");
    } else if (direcaoEmGraus  <= 90 && direcaoEmGraus > 45){
            Serial.print("Leste");
    } else if (direcaoEmGraus  <= 135 && direcaoEmGraus > 90){
            Serial.print("Sudeste");
    } else if (direcaoEmGraus  <= 180 && direcaoEmGraus > 135){ 
            Serial.print("Sul");
    } else if (direcaoEmGraus  <= 225 && direcaoEmGraus > 180){ 
            Serial.print("Sudoeste");
    } else if (direcaoEmGraus  <= 270 && direcaoEmGraus > 225){
            Serial.print("Oeste");
    } else if (direcaoEmGraus  <= 315 && direcaoEmGraus > 270){
            Serial.print("Noroeste"); 
    } else if (direcaoEmGraus  <= 360 && direcaoEmGraus > 315){
            Serial.print("Norte");       
    } else Serial.print("Norte");       
      Serial.print("\n");
}

void loop(){
    int graus = leituraEixos();
    enviarGraus(graus);
    //imprimir(graus);//impresso para teste
    //valor para demais testes = 100, para o Java = 500
    delay(550);
}
