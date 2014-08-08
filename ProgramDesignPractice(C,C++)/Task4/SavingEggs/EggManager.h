#pragma once
#include "GameObjectManager.h"
#include "stdafx.h"

class EggManager:
	public GameObjectManager
{
public:
	void updateAll(float);
};