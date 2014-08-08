#include "stdafx.h"
#include "CookManager.h"
#include "Cook.h"

CookManager::CookManager():
	_state0(Wait),
	_state1(Wait),
	_state2(Wait)
{

}

void CookManager::drawAll(sf::RenderWindow& rw)
{
	switch (_state0)
	{
	case CookManager::Wait:
		((Cook*)(get("wait0")))->draw(rw);
		break;
	case CookManager::Good:
		((Cook*)(get("good0")))->draw(rw);
		break;
	case CookManager::Bad:
		((Cook*)(get("bad0")))->draw(rw);
		break;
	default:
		break;
	}
	switch (_state1)
	{
	case CookManager::Wait:
		((Cook*)(get("wait1")))->draw(rw);
		break;
	case CookManager::Good:
		((Cook*)(get("good1")))->draw(rw);
		break;
	case CookManager::Bad:
		((Cook*)(get("bad1")))->draw(rw);
		break;
	default:
		break;
	}
	switch (_state2)
	{
	case CookManager::Wait:
		((Cook*)(get("wait2")))->draw(rw);
		break;
	case CookManager::Good:
		((Cook*)(get("good2")))->draw(rw);
		break;
	case CookManager::Bad:
		((Cook*)(get("bad2")))->draw(rw);
		break;
	default:
		break;
	}
}

void CookManager::setState(int index, CookState state)
{
	switch (index)
	{
	case 0:
		_state0 = state;
		break;
	case 1:
		_state1 = state;
		break;
	case 2:
		_state2 = state;
		break;
	default:
		break;
	}
}