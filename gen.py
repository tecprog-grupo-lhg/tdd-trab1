from random import randrange

def gen_evolution(i):
    result = f'---------- Evolution {i} ----------\n'
    number_of_lines = randrange(1, 50)
    for i in range(number_of_lines):
        time = randrange(200, 1500)
        result += f'{time}\n'
    return result

files = []
for i in range(50):
    number_of_evolutions = randrange(10, 40)
    aux = ''
    for j in range(number_of_evolutions):
        aux += gen_evolution(j+1)
    files.append(aux)   

for i, f in enumerate(files):
    x = open(f'analysis/analysis{i+1}.out', 'w')
    x.write(f)
