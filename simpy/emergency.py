import simpy
import random
import heapq

# Configuración de la semilla para reproducibilidad
random.seed(42)

# Parámetros de la simulación
TIEMPO_SIMULACION = 480
INTERVALO_LLEGADA = 10
TIEMPO_ASIGNACION = 5
TIEMPO_ATENCION = {1: 30, 2: 45, 3: 60, 4: 75, 5: 90}

# Recursos del hospital
NUM_MEDICOS = 3
NUM_SALAS = 5

# Cola de prioridad: (prioridad, tiempo_evaluacion, contador_paciente, objeto_paciente)
cola_pacientes = []
contador_global = 0  # Para evitar empate por nombre

class Paciente:
    def __init__(self, env, nombre):
        self.env = env
        self.nombre = nombre
        self.prioridad = None
        self.evaluado_en = None
        self.env.process(self.proceso_paciente())

    def proceso_paciente(self):
        global contador_global
        llegada = self.env.now
        print(f'{self.nombre} llega al hospital en el minuto {llegada:.2f}')
        
        # Evaluación
        yield self.env.timeout(TIEMPO_ASIGNACION)
        self.prioridad = random.randint(1, 5)
        self.evaluado_en = self.env.now
        print(f'{self.nombre} es evaluado y se le asigna prioridad {self.prioridad} en el minuto {self.evaluado_en:.2f}')
        
        # Se añade a la cola de prioridad
        contador_global += 1
        heapq.heappush(cola_pacientes, (self.prioridad, self.evaluado_en, contador_global, self))

def gestor_atencion(env, medicos, salas):
    while True:
        if cola_pacientes and medicos.count < medicos.capacity and salas.count < salas.capacity:
            _, _, _, paciente = heapq.heappop(cola_pacientes)

            with medicos.request() as req_medico, salas.request() as req_sala:
                yield req_medico & req_sala
                inicio = env.now
                print(f'{paciente.nombre} comienza atención médica en el minuto {inicio:.2f}')
                yield env.timeout(TIEMPO_ATENCION[paciente.prioridad])
                print(f'{paciente.nombre} finaliza atención médica en el minuto {env.now:.2f}')
        else:
            yield env.timeout(0.5)

def llegada_pacientes(env):
    i = 0
    while True:
        yield env.timeout(random.expovariate(1.0 / INTERVALO_LLEGADA))
        i += 1
        Paciente(env, f'Paciente-{i}')

# Inicialización del entorno de simulación
env = simpy.Environment()
medicos = simpy.Resource(env, capacity=NUM_MEDICOS)
salas = simpy.Resource(env, capacity=NUM_SALAS)

env.process(llegada_pacientes(env))
env.process(gestor_atencion(env, medicos, salas))

env.run(until=TIEMPO_SIMULACION)
