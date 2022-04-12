import pandas
import matplotlib.pyplot as plt
import cx_Oracle
import os
from time import time
from random import randint


#ToDo analisar consumo, tipooo, bastante em pouco tempo é mt bom
#ToDo pegar os dados do consumo e verificar o cnpj pra ver o cnae
print("start")
start = time()


def analyse_consumo(consumo_separated):
    score = []
    multiplier = 0.25
    size = len(consumo_separated)
    # Números inventandos, eu queria limitar as variaveis desse "score" pro maximo ser 4 e o mínimo 0.3
    # Se for num "streak" de coisas ruins (menos q o mes passado e valor mt baixo) é punido baseado no consumo da vez
    # Streak alto eleva baseado no consumo da vez. Assim achei q ficou mais dinamico e mais interessante
    for valor in consumo_separated:
        if sum(score) > 65:
            multiplier = 0.25
        if consumo_separated.index(valor) == 0:
            pass
        else:
            if valor >= consumo_separated[consumo_separated.index(valor) - 1] * 1.15:
                score.append(2.7)
                if valor >= 2300:
                    score.append(3.7 + valor * 0.005)
                elif valor >= 1400:
                    score.append(1.7)
                elif valor >= 800:
                    score.append(0.7)
                else:
                    score.append(0.4 - valor * 0.007)
            elif valor <= consumo_separated[consumo_separated.index(valor) - 1] * 0.75:
                score.append(1)
                if valor >= 2300:
                    score.append(2.7 + valor * 0.005)
                elif valor >= 1400:
                    score.append(1.5)
                elif valor >= 800:
                    score.append(0.7)
                else:
                    score.append(0.4 - valor * 0.007)
            else:
                score.append(1.5)
                if valor >= 2300:
                    score.append(2.7 + valor * 0.005)
                elif valor >= 1400:
                    score.append(1.5)
                elif valor >= 800:
                    score.append(0.7)
                else:
                    score.append(0.3 - valor * 0.007)
    # Faz uma média desse "score" e do consumo pra gerar um score final.
    # Vendo quem tem mais scorefinal da pra conferir se tem outras empresas de ramos semelhantes do concorrente ou livre
    print(f"soma {(sum(score) / size) * 20} -- consumos {(sum(consumo_separated) / size) * multiplier}")
    final_score = (sum(score) / size) * 20 + (sum(consumo_separated) / size) * multiplier
    if final_score > 1000:
        final_score = 1000
    return int(final_score)


db202203301935_low = '(description= (retry_count=20)(retry_delay=3)(address=(protocol=tcps)(port=1522)(host=adb.sa-saopaulo-1.oraclecloud.com))(connect_data=(service_name=g5a8d282e2d63db_db202203301935_low.adb.oraclecloud.com))(security=(ssl_server_cert_dn="CN=adb.sa-saopaulo-1.oraclecloud.com, OU=Oracle ADB SAOPAULO, O=Oracle Corporation, L=Redwood City, ST=California, C=US")))'

cx_Oracle.init_oracle_client(lib_dir=os.path.dirname(os.path.abspath(__file__)) + "\oracleBasic")

connection = cx_Oracle.connect(user="ADMIN", password="BDrelacional5", dsn="db202203301935_low")
print(cx_Oracle.version)
cursor = connection.cursor()

# Pega os dados do consumo baseado
# c = cursor.execute("SELECT * FROM consumo c INNER JOIN empresa e ON e.emp_cnpj = c.emp_cnpj WHERE e.emp_origem = 'CONCORRENTE'")
# conc = []
# for i in c:
#     conc.append(i)

#1500 empresas do spc e conc, cada
c = cursor.execute("SELECT * FROM consumo c INNER JOIN empresa e ON e.emp_cnpj = c.emp_cnpj WHERE e.emp_origem = 'SPC' ORDER BY c.emp_cnpj, c.cons_mesref")
spc = []
for i in c:
    spc.append(i)
#print(spc)


consumo = []
score = []
for data in spc:
    if spc.index(data) + 1 == len(spc):
        print(data[2])
        consumo.append(data[2])
        print(f"TOTAL DO CLIENTE {data[1]} - {sum(consumo)}")
        sc = analyse_consumo(consumo)
        print(f"SCORE: {sc}\n-------------------")
        if sc > 820 or sc < 230:
            score.append({"consumos": consumo, "score": sc})
        else:
            if randint(1, 20) > 11:
                score.append({"consumos": consumo, "score": sc})
        consumo = []
    else:
        if data[1] == spc[spc.index(data) + 1][1]:
            print(data[2])
            consumo.append(data[2])
        else:
            print(data[2])
            consumo.append(data[2])
            print(f"TOTAL DO CLIENTE {data[1]} - {sum(consumo)}")
            sc = analyse_consumo(consumo)
            print(f"SCORE: {sc}\n-------------------")
            if sc > 980 or sc < 220:
                if randint(1, 20) > 8:
                    score.append({"consumos": consumo, "score": sc})
            else:
                if randint(1, 20) > 18:
                    score.append({"consumos": consumo, "score": sc})
            consumo = []

print(len(score))
for i in score:
    pass
    #print(i)



# print(empresa)
# empresas = pandas.read_csv(filepath_or_buffer="base_empresas.csv")
# consumo = pandas.read_csv(filepath_or_buffer="base_consumo.csv")
# cnae = pandas.read_csv(filepath_or_buffer="base_cnae.csv", encoding="windows-1252")
#
# print(consumo)
# print(empresas)
# print(cnae)
#

# for i, row in consumo.iterrows():
#     print(i)
#     print(row)
#     print(row["NUM_CNPJ"])
#     print("$" * 20)
# print(empresas._get_value(2, "NUM_CNPJ"))
# print(empresas["NUM_CNPJ"][2])
#
# print(empresas["NUM_CNPJ"].is_unique)


# empresas.cumsum()
# plt.figure()
# empresas.plot()
# plt.show()
#
# plt.savefig("graph.png")

# Gera um txt na raiz só pra saber se rodou
with open("dados.txt", "w") as f:
    f.write(str(score))

cursor.close()
connection.close()

end = time()
print(f"finish\nTime: {end - start}")