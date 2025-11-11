from threading import Thread, Lock

mutex = Lock()
count = 0

def increaseCount():
    global count
    mutex.acquire()
    count += 1
    mutex.release()


thread1 = Thread(target=increaseCount)
thread2 = Thread(target=increaseCount)
thread1.start()
thread2.start()
thread1.join()
thread2.join()

print(f'Count = {count}')


