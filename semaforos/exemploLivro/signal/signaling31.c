#include<stdio.h>
#include<pthread.h>
#include<semaphore.h>
#include <unistd.h>

sem_t a_arrive;

void* thread_a_func(void* arg){
    printf("Thread A Chegou!\n");
    sem_post(&a_arrive);
    return NULL;
}

void *thread_b_func(void* arg){
    sem_wait(&a_arrive);
    printf("Thread B Chegou!\n");
    return NULL;
}

int main(int argc, char const *argv[])
{
    pthread_t t1, t2;

    sem_init(&a_arrive,0,0);

    printf("Programa principal: Iniciando threads.\n");

    pthread_create(&t2,NULL, thread_b_func, NULL);

    sleep(1);

    pthread_create(&t1, NULL, thread_a_func, NULL);

    pthread_join(t1,NULL);
    pthread_join(t2,NULL);

    sem_destroy(&a_arrive);
    printf("Programa principal terminado.\n");

    return 0;
}
