#include <pthread.h>
#include <semaphore.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h> 

sem_t multiplex;

void* party (void* args){
    int id = *((int*)args);
    sem_wait(&multiplex);
    printf("+++ Thread %d ENTROU na festa!\n", id);
    sleep(2);
    printf("--- Thread %d SAIU da festa.\n", id);
    sem_post(&multiplex);
    free(args);
    return NULL;
}

int main(int argc, char const *argv[])
{
    int num_threads;
    int num_threads_can_run;

    printf("Digite o número total de threads: ");

    if (scanf("%d", &num_threads) != 1 || num_threads <= 0) {
        printf("Entrada inválida para total de threads.\n");
        return 1;
    }

    printf("Digite quantas threads podem entrar por vez: ");
    if (scanf("%d", &num_threads_can_run) != 1 || num_threads_can_run <= 0) {
        printf("Entrada inválida para vagas.\n");
        return 1;
    }

    sem_init(&multiplex, 0, num_threads_can_run);
    pthread_t threads[num_threads];
    printf("\nIniciando a festa (%d vagas)...\n\n", num_threads_can_run);

    for (int i = 0; i < num_threads; i++) {
        int *thread_id = malloc(sizeof(int));
        *thread_id = i;
        pthread_create(&threads[i], NULL, party, thread_id);
    }

    for (int i = 0; i < num_threads; i++) {
        pthread_join(threads[i], NULL);
    }

    sem_destroy(&multiplex);
    printf("\nA festa acabou!\n");

    return 0;
}