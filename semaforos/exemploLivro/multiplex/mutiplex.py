from threading import Thread, Semaphore, current_thread
from time import sleep


number_threads = int(input())
number_threads_can_run = int(input())

multiplex = Semaphore(number_threads_can_run)

def party():
    name = current_thread().name 
    multiplex.acquire()
    try:
        print(f"+++ {name} ENTROU na festa")
        sleep(2)
    finally:
        print(f"--- {name} SAIU da festa")
        multiplex.release()

threads = []
for i in range(number_threads):
    new_thread = Thread(target=party, name=f'Thread {i}')
    threads.append(new_thread)
    new_thread.start()

for t in threads:
    t.join()