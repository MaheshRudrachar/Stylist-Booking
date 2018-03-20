for i in 1 2 3; do
    docker-machine create -d virtualbox swarm-node-$i
done

eval $(docker-machine env swarm-node-1)

docker swarm init \
    --advertise-addr $(docker-machine ip swarm-node-1)

docker swarm join-token -q manager

TOKEN=$(docker swarm join-token -q worker)

for i in 2 3; do
  eval $(docker-machine env swarm-node-$i)

  docker swarm join \
    --token $TOKEN \
    --advertise-addr $(docker-machine ip swarm-node-$i) \
    $(docker-machine ip swarm-node-1):2377
done

eval $(docker-machine env swarm-node-1)

docker node ls

exit

