import pandas
import matplotlib.pyplot as plt
import cx_Oracle
import os
from time import time

#ToDo analisar consumo, tipooo, bastante em pouco tempo é mt bom
#ToDo pegar os dados do consumo e verificar o cnpj pra ver o cnae
print("start")
start = time()

# ToDo usar decouple para pegar esses dados sem ir pro git por um .env
db_high = 'CON DESC GOES HERE'


cx_Oracle.init_oracle_client(lib_dir= r"PATH TO ORACLE BASIC")

connection = cx_Oracle.connect(user="USER", password="PASSWORD", dsn="db_high")
print(cx_Oracle.version)
cursor = connection.cursor()

c = cursor.execute("SELECT * FROM consumo")

for i in c:
    print(i)


# empresas = pandas.read_csv(filepath_or_buffer="base_empresas.csv")
#consumo = pandas.read_csv(filepath_or_buffer="base_consumo.csv")
# cnae = pandas.read_csv(filepath_or_buffer="base_cnae.csv", encoding="windows-1252")
#
# print(consumo)
# print(empresas)
# print(cnae)
#

# for i, row in consumo.iterrows():
#      print(i)
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

end = time()
print(f"finish\nTime: {end - start}")