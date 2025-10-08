#!/usr/bin/env python3


import os
import sys
import requests
from typing import List, Dict, Optional

def get_repo_collaborators(token: str,owner: str,repo: str,affiliation: str = "all") -> List[Dict]:

    headers = {
        "Authorization": f"token {token}",
        "Accept": "application/vnd.github.v3+json"
    }

    url = f"https://api.github.com/repos/{owner}/{repo}/collaborators"
    params = {
        "affiliation": affiliation,
        "per_page": 100  # Максимальное количество на страницу
    }

    collaborators = []
    page = 1

    while True:
        params["page"] = page
        response = requests.get(url, headers=headers, params=params)

        if response.status_code != 200:
            print(f"Ошибка API: {response.status_code}")
            print(f"Сообщение: {response.json().get('message', 'Unknown error')}")
            sys.exit(1)

        data = response.json()
        if not data:
            break

        collaborators.extend(data)
        page += 1

        if "next" not in response.links:
            break

    return collaborators

def print_collaborators_info(collaborators: List[Dict]):

    print("=" * 60)
    print(f"УЧАСТНИКИ РЕПОЗИТОРИЯ")
    print("=" * 60)

    for i, collaborator in enumerate(collaborators, 1):
        login = collaborator.get("login", "N/A")
        html_url = collaborator.get("html_url", "N/A")
        role_name = collaborator.get("role_name", "N/A")
        permissions = collaborator.get("permissions", {})

        print(f"{i:2d}. {login}")
        print(f"     URL: {html_url}")
        print(f"     Роль: {role_name}")
        print(f"     Права: {', '.join([k for k, v in permissions.items() if v])}")
        print("-" * 40)

def main():
    token = os.getenv("GITHUB_TOKEN")
    repository = os.getenv("GITHUB_REPOSITORY")

    if not token:
        print("Ошибка: GITHUB_TOKEN не установлен")
        sys.exit(1)

    if not repository:
        print("Ошибка: GITHUB_REPOSITORY не установлен")
        sys.exit(1)

    owner, repo = repository.split("/")

    print(f"Получение участников репозитория: {owner}/{repo}")

    try:

        collaborators = get_repo_collaborators(token, owner, repo)

        if not collaborators:
            print("В репозитории нет участников или нет доступа к этой информации")
            return


        print_collaborators_info(collaborators)


        print(f"\nСтатистика:")
        print(f"Всего участников: {len(collaborators)}")


        roles = {}
        for collab in collaborators:
            role = collab.get("role_name", "unknown")
            roles[role] = roles.get(role, 0) + 1

        for role, count in roles.items():
            print(f"  - {role}: {count}")

    except Exception as e:
        print(f"Произошла ошибка: {e}")
        sys.exit(1)

if __name__ == "__main__":
    main()