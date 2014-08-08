#include "EggManager.h"
#include "stdafx.h"
#include "Egg.h"
#include "Game.h"

void EggManager::updateAll(float elapsedTime)
{
	if (!(((Egg*)get("egg0"))->isDropping()||((Egg*)get("egg1"))->isDropping()||((Egg*)get("egg2"))->isDropping()))
	{
		((Egg*)get("egg0"))->setState(true);
		((Egg*)get("egg1"))->setState(true);
		((Egg*)get("egg2"))->setState(true);
		for (int i=0; i<3; i++)
		{
			Game::op[i] = 1;
			Game::_cooks.setState(i, CookManager::Wait);
		}
		((Egg*)get("egg0"))->setVelocity(((Egg*)get("egg0"))->getVelocity()+40);
	}
	GameObjectManager::updateAll(elapsedTime);
}