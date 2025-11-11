#include<stdio.h>
#include<pthread.h>
#include<semaphore.h>
#include <unistd.h>

sem_t a_arrive;
sem_t b_arrive;

void* thread_a1_func(void* arg){
    printf("Thread A1 Chegou!\n");
    sem_post(&a_arrive);
    return NULL;
}

void* thread_a2_func(void* arg){
    sem_wait(&b_arrive);
    printf("Thread A2 Chegou!\n");
    return NULL;
}

void *thread_b1_func(void* arg){
    printf("Thread B1 Chegou!\n");
    sem_post(&b_arrive);
    return NULL;
}

void *thread_b2_func(void* arg){
    sem_wait(&a_arrive);
    printf("Thread B2 Chegou!\n");
    return NULL;
}


int main(int argc, char const *argv[])
{
    pthread_t a1,a2,b1,b2;
    sem_init(&a_arrive,0,0);
    sem_init(&b_arrive,0,0);
    pthread_create(&a1,NULL,thread_a1_func,NULL);
    pthread_create(&a2,NULL,thread_a2_func,NULL);
    pthread_create(&b1,NULL,thread_b1_func,NULL);
    pthread_create(&b2,NULL,thread_b2_func,NULL);

    pthread_join(b1,NULL);
    pthread_join(b2,NULL);
    pthread_join(a1,NULL);
    pthread_join(a2,NULL);

    return 0;
}
