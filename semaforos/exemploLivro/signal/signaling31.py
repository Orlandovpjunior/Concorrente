import threading

semaphore = threading.Semaphore(0)

def thread_function_a():
    print("A Chegou\n")
    semaphore.release()

def thread_function_b():
    print("Thread B: Esperando pelo sinal de A...\n")
    semaphore.acquire()
    print("B Chegou")

if __name__ == '__main__':
    t1 = threading.Thread(target=thread_function_a, name="A")
    t2 = threading.Thread(target=thread_function_b, name="B")

    t2.start()
    t1.start()
    t1.join()
    t2.join()
    