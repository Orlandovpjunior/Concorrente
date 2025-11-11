#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>

int count = 0;

sem_t mutex;

void* increaseCount(void* arg){
    sem_wait(&mutex);
    count++;
    sem_post(&mutex);
    return NULL;
}

int main(int argc, char const *argv[])
{
    pthread_t t1,t2;
    sem_init(&mutex,0,1);

    pthread_create(&t1, NULL, increaseCount,NULL);
    pthread_create(&t2, NULL, increaseCount,NULL);

    pthread_join(t1,NULL);
    pthread_join(t2,NULL);

    printf("%d\n", count);

    return 0;
}

