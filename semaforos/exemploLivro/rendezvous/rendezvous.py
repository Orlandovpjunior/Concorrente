import threading

aArrive = threading.Semaphore(0)
bArrive = threading.Semaphore(0)

def exibeA1():
    print("A1 Chegou")
    aArrive.release()

def exibeB1():
    print("B1 Chegou")
    bArrive.release()

def exibeB2():
    aArrive.acquire()
    print("B2 Chegou")

def exibeA2():
    bArrive.acquire()
    print("A2 Chegou")

if __name__ == "__main__":

    a1 = threading.Thread(target=exibeA1,name="A1")
    b1 = threading.Thread(target=exibeB1,name="B1")
    a2 = threading.Thread(target=exibeB2,name="B2")
    b2 = threading.Thread(target=exibeA2,name="A2")

    a1.start()
    a2.start()
    b1.start()
    b2.start()

    a1.join()
    a2.join()
    b1.join()
    b2.join()

